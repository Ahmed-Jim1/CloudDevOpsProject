  
module "network" {
  source        = "./modules/network"
}

module "security-groups" {
  source = "./modules/security-groups"
  my-vpc_id = module.network.vpc_id
  depends_on = [ module.network ]
}

module "compute" {
  source = "./modules/compute"
  my-vpc_id = module.network.vpc_id
  public-subnet_id = module.network.public_subnet_id
  ec2_sg_id = module.security-groups.ec2_sg_id
  depends_on = [ module.network ]
  }






