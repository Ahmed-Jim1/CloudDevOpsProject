output "jenkins_slave_ip" {
  value = module.compute.public_ec2_id
}
output "jenkins_master_ip" {
  value = module.compute.public2_ec2_id
}
