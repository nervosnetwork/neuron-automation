#!/usr/bin/env bash
NEURON_VERSION=$(cat .neuron-version)
NEURON_DIR=$(pwd)/resourcedownload
NEURON_WIN_FILE_PATH=${NEURON_DIR}/Neuron-${NEURON_VERSION}-setup.exe
NEURON_MAC_FILE_PATH=${NEURON_DIR}/Neuron-${NEURON_VERSION}.dmg
NEURON_LINUX_FILE_PATH=${NEURON_DIR}/Neuron-${NEURON_VERSION}-x86_64.AppImage

function download_windows(){
  echo "NEURON_WIN_FILE_PATH: " ${NEURON_WIN_FILE_PATH}
  mkdir -p ${NEURON_DIR}
  test -e ${NEURON_WIN_FILE_PATH} && echo "Installation file already existed!" || curl -L -o ${NEURON_WIN_FILE_PATH} "https://github.com/nervosnetwork/neuron/releases/download/${NEURON_VERSION}/Neuron-${NEURON_VERSION}-setup.exe"
}

function download_macos(){
  echo "NEURON_MAC_FILE_PATH: " ${NEURON_MAC_FILE_PATH}
  mkdir -p ${NEURON_DIR}
  test -e ${NEURON_MAC_FILE_PATH} && echo "Installation file already existed!" || curl -L -o ${NEURON_MAC_FILE_PATH} "https://github.com/nervosnetwork/neuron/releases/download/${NEURON_VERSION}/Neuron-${NEURON_VERSION}.dmg"
}

function download_linux() {
  echo "NEURON_MAC_FILE_PATH: " ${NEURON_LINUX_FILE_PATH}
  mkdir -p ${NEURON_DIR}
  test -e ${NEURON_LINUX_FILE_PATH} && echo "Installation file already existed!" || curl -L -o ${NEURON_MAC_FILE_PATH} "https://github.com/nervosnetwork/neuron/releases/download/${NEURON_VERSION}/Neuron-${NEURON_VERSION}-x86_64.AppImage"
}

function install_win(){
  if test -e ${NEURON_WIN_FILE_PATH}
  then
    ${NEURON_WIN_FILE_PATH} /S
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
    echo "      Default Commands: download | install_win | system-test | send-test"
    echo "      ---------------------------------------------"
    echo "      download             download Neuron installation binary, under the resource download folder"
    echo "      install_win          install Neuron from downloaded windows installation binary "
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
    download_macos) download_macos;;
    download_linux) download_linux;;
    install_win) install_win;;
    system-test) system-test;;
    send-test) sendtx-test;;
    help|-h|--help) usage;;
    *) usage;;
  esac
fi