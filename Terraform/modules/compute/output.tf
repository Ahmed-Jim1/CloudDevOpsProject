output "public_ec2_id" {
  value = aws_instance.jenkins_slave.public_ip
}
output "public2_ec2_id" {
  value = aws_instance.jenkins_master.public_ip
}



 