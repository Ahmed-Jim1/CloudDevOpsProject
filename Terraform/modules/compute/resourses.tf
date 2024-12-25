resource "aws_instance" "jenkins_slave" {
  ami                         = var.ami
  instance_type               = "t2.micro"
  subnet_id                   = var.public-subnet_id
  key_name = "ivolve"
  vpc_security_group_ids      = [var.ec2_sg_id]
  associate_public_ip_address = true
  user_data = <<-EOF
              #!/bin/bash
              yum update -y
              yum install -y httpd
              systemctl start httpd
              systemctl enable httpd
              echo "Hello, World from EC2!" > /var/www/html/index.html
              EOF

  tags = {
    Name = "jenkins_slave"
  }
  lifecycle {
    create_before_destroy = true
  }
}
resource "aws_cloudwatch_metric_alarm" "cpu_alarm" {
  alarm_name          = "high_cpu_utilization"
  comparison_operator = "GreaterThanThreshold"
  evaluation_periods  = 1
  metric_name         = "CPUUtilization"
  namespace           = "AWS/EC2"
  period              = 60
  statistic           = "Average"
  threshold           = 70
  alarm_description   = "Alarm when CPU exceeds 70%"
  dimensions = {
    InstanceId = aws_instance.jenkins_slave.id
  }
  alarm_actions = [aws_sns_topic.alert_topic.arn]
}
resource "aws_sns_topic" "alert_topic" {
  name = "cpu_alert_topic"
}

resource "aws_sns_topic_subscription" "email_subscription" {
  topic_arn = aws_sns_topic.alert_topic.arn
  protocol  = "email"
  endpoint  = var.email_subscription_email
}