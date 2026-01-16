package com.example.demo.service;
import com.example.demo.controller.ArduinoController;
import com.fazecast.jSerialComm.SerialPort;
import org.springframework.stereotype.Service;
import jakarta.annotation.PostConstruct;

@Service
public class SerialService {

    @PostConstruct // Faz o Java ligar a porta USB assim que o projeto der START
    public void iniciarLeitura() {
        SerialPort porta = SerialPort.getCommPort("COM3");
        porta.setBaudRate(9600);

        if (porta.openPort()) {
            System.out.println("✅ CONECTADO AO ARDUINO NA PORTA " + porta.getSystemPortName());
        } else {
            System.out.println("❌ ERRO: Não consegui abrir a porta USB. O Arduino está plugado?");
            return;
        }

        // Thread para ler em tempo real sem travar o resto do site
        new Thread(() -> {
            try {
                while (true) {
                    if (porta.bytesAvailable() > 0) {
                        byte[] buffer = new byte[porta.bytesAvailable()];
                        porta.readBytes(buffer, buffer.length);
                        String mensagem = new String(buffer).trim();

                        if (mensagem.contains("OPCAO_A")) {
                            ArduinoController.setComando("OPCAO_A");
                        } else if (mensagem.contains("OPCAO_B")) {
                            ArduinoController.setComando("OPCAO_B");
                        }
                    }
                    Thread.sleep(100); // Descansa um pouco o processador
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}