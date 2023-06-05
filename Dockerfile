FROM quay.io/quarkus/quarkus-micro-image:2.0

# Change workdir
WORKDIR /etc/hcf

# Copy the native executable
COPY ./target/*-runner /etc/hcf/persistent-server

# Set up permissions for user `1001`
RUN chmod 775 /etc/hcf/persistent-server \
  && chown -R 1001 /etc/hcf \
  && chmod -R "g+rwX" /etc/hcf \
  && chown -R 1001:root /etc/hcf

RUN ls -la /etc/hcf

# Run as non-root and expose port 3030
USER 1001
EXPOSE 3030

# Run the native executable
CMD ["./persistent-server", "-Dquarkus.http.host=0.0.0.0"]
