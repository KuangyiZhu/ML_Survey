FROM public.ecr.aws/amazonlinux/amazonlinux:latest

# Install dependencies
RUN yum update -y && \
 yum install -y passwd && \
 yum install -y which && \
 yum install -y procps-ng && \
 yum install -y java-21* && \
 yum install -y git-all


RUN groupadd -r appdev 

# Add a user named "myuser" belonging to the "appdev" group
RUN useradd -r -g appdev -m -d /home/appdev appdev

# Switch to the new user
USER appdev

# Set the working directory
WORKDIR /home/appdev
CMD ["sh"]
