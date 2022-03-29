#!/usr/bin/env bash
NEURON_VERSION=$(curl --silent "https://api.github.com/repos/nervosnetwork/neuron/releases/latest" | grep '"tag_name":' | sed -E 's/.*"([^"]+)".*/\1/')
CKB_VERSION=$(curl https://raw.githubusercontent.com/nervosnetwork/neuron/master/.ckb-version)
NEURON_DIR=$(pwd)/resourcedownload
NEURON_WIN_FILE_PATH=${NEURON_DIR}/Neuron-${NEURON_VERSION}-setup.exe
NEURON_MAC_FILE_PATH=${NEURON_DIR}/Neuron-${NEURON_VERSION}.dmg
NEURON_LINUX_FILE_PATH=${NEURON_DIR}/Neuron-${NEURON_VERSION}-x86_64.AppImage
CKB_WIN_FILE_PATH=${NEURON_DIR}/ckb_${CKB_VERSION}_x86_64-pc-windows-msvc
CKB_MAC_FILE_PATH=${NEURON_DIR}/ckb_${CKB_VERSION}_x86_64-apple-darwin
CKB_LINUX_FILE_PATH=${NEURON_DIR}/ckb_${CKB_VERSION}_x86_64-unknown-linux-gnu

function download_ckb_windows(){
  echo "CKB_WIN_FILE_PATH: " ${CKB_WIN_FILE_PATH}
  mkdir -p ${NEURON_DIR}
  test -e ${CKB_WIN_FILE_PATH}.zip && echo "CKB installation file already existed!" || curl -L -o ${CKB_WIN_FILE_PATH}.zip "https://github.com/nervosnetwork/ckb/releases/download/${CKB_VERSION}/ckb_${CKB_VERSION}_x86_64-pc-windows-msvc.zip"

}

function unzip_ckb_windows(){
  test -e ${CKB_WIN_FILE_PATH}.zip && unzip -o ${CKB_WIN_FILE_PATH}.zip -d ${NEURON_DIR}|| echo "CKB installation file doesn't exist!"
  test -e ${NEURON_DIR}/ckb && cp -rf ${CKB_WIN_FILE_PATH}/* ${NEURON_DIR}/ckb/ && rm -r ${CKB_WIN_FILE_PATH} || mv -f ${CKB_WIN_FILE_PATH} ${NEURON_DIR}/ckb
}

function download_ckb_mac(){
  echo "CKB_MAC_FILE_PATH: " ${CKB_MAC_FILE_PATH}
  mkdir -p ${NEURON_DIR}
  test -e ${CKB_MAC_FILE_PATH}.zip && echo "CKB installation file already existed!" || curl -L -o ${CKB_MAC_FILE_PATH}.zip "https://github.com/nervosnetwork/ckb/releases/download/${CKB_VERSION}/ckb_${CKB_VERSION}_x86_64-apple-darwin.zip"
  unzip ${CKB_MAC_FILE_PATH}.zip
  mv ${CKB_MAC_FILE_PATH} ckb
}

function download_ckb_linux(){
  echo "CKB_LINUX_FILE_PATH: " ${CKB_LINUX_FILE_PATH}
  mkdir -p ${NEURON_DIR}
  test -e ${CKB_LINUX_FILE_PATH}.tar.gz && echo "CKB installation file already existed!" || curl -L -o ${CKB_LINUX_FILE_PATH}.tar.gz "https://github.com/nervosnetwork/ckb/releases/download/${CKB_VERSION}/ckb_${CKB_VERSION}_x86_64-unknown-linux-gnu.tar.gz"
  tar -xzvf ${CKB_LINUX_FILE_PATH}.tar.gz
  mv ${CKB_LINUX_FILE_PATH} ckb
}

function download_windows(){
  echo "NEURON_WIN_FILE_PATH: " ${NEURON_WIN_FILE_PATH}
  mkdir -p ${NEURON_DIR}
  test -e ${NEURON_WIN_FILE_PATH} && echo "Installation file already existed!" || curl -L -o ${NEURON_WIN_FILE_PATH} "https://github.com/nervosnetwork/neuron/releases/download/${NEURON_VERSION}/Neuron-${NEURON_VERSION}-setup.exe"
}

function download_mac(){
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
  echo "On Win OS install Neuron " ${NEURON_VERSION}
  cmd "/c start cmd.exe /c" %cd%\\scripts\\install_neuron_win.bat ${NEURON_VERSION}
}

function system-test(){
  mvn clean test -DsuiteXmlFile=systemTest.xml
}

usage(){
    echo "      Usage: $0 COMMAND [args...]"
    echo "      Default Commands: download | install_win | system-test | send-test"
    echo "      ---------------------------------------------"
    echo "      download             download Neuron installation binary, under the resourcedownload folder"
    echo "      downloadckb          download ckb installation binary, under the resourcedownload folder"
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
    downloadckb) if [[ "$OSTYPE" == "darwin"* ]]; then
        download_ckb_mac
      elif [[ "$OSTYPE" == "linux-gnu" ]]; then
        download_ckb_linux
      else
        download_ckb_windows
      fi
    ;;
    download_ckb_windows) download_ckb_windows;;
    download_ckb_mac) download_ckb_mac;;
    download_ckb_linux) download_ckb_linux;;
    download_windows) download_windows;;
    download_mac) download_mac;;
    download_linux) download_linux;;
    unzip_ckb_windows) unzip_ckb_windows;;
    install_win) install_win;;
    system-test) system-test;;
    send-test) sendtx-test;;
    help|-h|--help) usage;;
    *) usage;;
  esac
fi