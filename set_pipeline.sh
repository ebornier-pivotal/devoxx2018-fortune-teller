#!/bin/bash

PIPELINE_NAME=${1}
ALIAS=${2}
CREDENTIALS=${3}

fly -t "${ALIAS}" sp -p "${PIPELINE_NAME}" -c ci/fortune-teller-pipeline.yml  -l "${CREDENTIALS}" -n
