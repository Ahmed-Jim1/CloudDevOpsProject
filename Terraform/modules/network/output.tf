output "vpc_id" {
  description = "The ID of the selected VPC"
  value       = data.aws_vpc.selected.id
}
output "public_subnet_id" {
  description = "The ID of the public subnet"
  value       = aws_subnet.public_subnet.id
}
