#!/usr/bin/env bash
NEURON_VERSION=$(cat .neuron-version)
NEURON_DIR=$(pwd)/resourcedownload
NEURON_FILE_PATH=${NEURON_DIR}/Neuron-${NEURON_VERSION}-setup.exe

function download_windows(){
  echo "NEURON_FILE_PATH: " ${NEURON_FILE_PATH}
  mkdir -p ${NEURON_DIR}
  test -e ${NEURON_FILE_PATH} && echo "Installation file already existed!" || curl -L -o ${NEURON_FILE_PATH} "https://github.com/nervosnetwork/neuron/releases/download/${NEURON_VERSION}/Neuron-${NEURON_VERSION}-setup.exe"

}

function install(){
  if test -e ${NEURON_FILE_PATH}
  then
    ${NEURON_FILE_PATH} /q
  else
	  echo -e $(date +"%Y-%m-%d %H:%M:%S") "ERROR: Installation file does not exist! Please check under the folder: ${NEURON_DIR}. \n" >>error-sh.log
  fi
}

function system-test(){
  mvn clean test -DsuiteXmlFile=systemTest.xml
}

function sendtx-test(){
  mvn clean test -DsuiteXmlFile=sendTXTest.xml
 }

usage(){
    echo "      Usage: $0 COMMAND [args...]"
    echo "      Default Commands: download | install | system-test | send-test"
    echo "      ---------------------------------------------"
    echo "      download             download Neuron installation binary, under the resourcedownload folder"
    echo "      install              install Neuron from downloaded installation binary "
    echo "      system-test          run system test"
    echo "      send-test            run send test"
    echo "      -h,--help,help       display help information"
}

if [ $# -lt 1 ];
then
    usage
else
  case $1 in
    download) if [[ "$OSTYPE" == "darwin"* ]]; then
        download_macos
      elif [[ "$OSTYPE" == "linux-gnu" ]]; then
        download_linux
      else
        download_windows
      fi
    ;;
    download_windows) download_windows;;
    install) install;;
    system-test) system-test;;
    send-test) sendtx-test;;
    help|-h|--help) usage;;
    *) usage;;
  esac
fi