::echo input version,e.g. v0.31.0-rc2
set NEURON_VERSION=%1%
@set PWD=%cd%
@set NEURON_DIR=%PWD%\resourcedownload\
@set NEURON_FILE_PATH=%NEURON_DIR%Neuron-%NEURON_VERSION%-setup.exe
@if exist %NEURON_FILE_PATH% (
ECHO Neuron installation is in progress...
start /wait %NEURON_FILE_PATH% /S
if %errorlevel%==0 (echo  Neuron installation -- OK) else (echo  Neuron installation -- error)
ECHO .
) else (
ECHO Installation package not available.
)
ping 127.1 -n 11 >nul
