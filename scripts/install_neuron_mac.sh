#!/usr/bin/env bash
NEURON_VERSION=$(cat .neuron-version)
NEURON_VERSION2="Neuron ${NEURON_VERSION:1}"
#echo ${NEURON_VERSION}
echo "${NEURON_VERSION2} for MacOS installing..."

hdiutil attach $1

cp -rf "/Volumes/${NEURON_VERSION2}/Neuron.app" /Applications
hdiutil detach "/Volumes/${NEURON_VERSION2}"