# download installation file from url like: https://github.com/nervosnetwork/neuron/releases/download/v0.29.0/Neuron-v0.29.0-setup.exe
download-neuron:
	./scripts/neuron.sh download

install-neuron:
	./scripts/neuron.sh install

system-test:
	@echo "[test] run regression tests"
	# run tests and generate test report
	./scripts/neuron.sh system-test

send-test:
	@echo "[test] run send tx tests"
	./scripts/neuron.sh send-test

download-install-neuron:
	make download-neuron
	make install-neuron
