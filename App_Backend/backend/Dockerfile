FROM python:3.10-slim-bullseye
ENV LANG=C.UTF-8

RUN apt-get update && apt-get install -y libpq-dev gcc

RUN mkdir -p /var/www/backend
WORKDIR /var/www/backend
COPY requirements.txt .
RUN pip3 install -r requirements.txt
COPY .. .
RUN mkdir -p static logs
EXPOSE 8000

ENTRYPOINT ["./docker-entrypoint.sh"]
