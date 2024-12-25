variable "ami" {
  default = "ami-0453ec754f44f9a4a"
}
variable "my-vpc_id" {
  type = string
}

variable "ec2_sg_id" {
  type = string
}
variable "public-subnet_id" {
  type = string
}
variable "email_subscription_email" {
default = "ahmedjim970@gmail.com" 
}
