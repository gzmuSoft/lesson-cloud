FROM rabbitmq:3.7-management
MAINTAINER Rui
COPY rabbitmq_delayed_message_exchange.ez ./plugins
RUN rabbitmq-plugins enable rabbitmq_delayed_message_exchange