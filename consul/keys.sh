#!/bin/sh

consul kv put -http-addr=http://consul:8500  config/graphql/data @/usr/local/bin/data.yml
consul kv put   config/graphql/data @./consul/data.yml