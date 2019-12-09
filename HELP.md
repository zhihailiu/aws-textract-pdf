# Getting Started

## Configure AWS SDK
1. ~/.aws/config
[default]
region=us-east-1

2. ~/.aws/credentials
[default]
aws_access_key_id =
aws_secret_access_key = 

## Build
$mvn clean package
$jar -jar ./target/aws-textract-pdf-0.0.1-SNAPSHOT.jar

## Test
$curl -X POST -F file=@TeraThink.png -o TeraThink.pdf 'http://localhost:9090/png'