package com.mx.org.concentradora.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MockEchoClient {

	private Socket clientSocket;
	private PrintWriter out;
	private BufferedReader in;

	public static void main(String[] args) {
		System.out.println("Inciando cliente");
		MockEchoClient client = new MockEchoClient();
		client.startConnection("192.168.1.82", 9898);
		String response = client.sendMessage(GeneradorPeticionesMock.generarPeticionEcho());
		System.out.println("La respuesta a la solicitud de echo fue: " + response);
		client.stopConnection();
	}

	public void startConnection(String ip, int port) {
		try {
			System.out.println("abriendo cleinte con: " + ip + ":" + port);
			clientSocket = new Socket(ip, port);
			out = new PrintWriter(clientSocket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		} catch (IOException ex) {

			System.out.println("Ocurrio un error: " + ex.getMessage());
			ex.printStackTrace();
		}

	}

	public String sendMessage(String msg) {

		String resp = "";
		try {
			System.out.println("enviando mensaje: " + msg);
			out.println(msg);
			// out.flush();
			resp = in.readLine();
		} catch (IOException ex) {
			System.out.println("Ocurrio un error: " + ex.getMessage());
			ex.printStackTrace();
		}
		return resp;
	}

	public void stopConnection() {
		try {
			in.close();
			out.close();
			clientSocket.close();
		} catch (IOException ex) {
			Logger.getLogger(MockEchoClient.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

}
