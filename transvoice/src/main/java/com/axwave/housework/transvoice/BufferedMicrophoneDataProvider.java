package com.axwave.housework.transvoice;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.util.Arrays;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioFormat.Encoding;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.Line;
import javax.sound.sampled.Line.Info;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.Port;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.TargetDataLine;

import com.google.common.base.Preconditions;

public class BufferedMicrophoneDataProvider implements DataProvider {
	
	private TargetDataLine microphone;
	private AudioFormat format;
	private int bufferSize;
	private Port port;
	
	public BufferedMicrophoneDataProvider(int bufferSize) throws DataProviderException {
		Preconditions.checkArgument(bufferSize > 32 );
		this.bufferSize = bufferSize;
		if(!AudioSystem.isLineSupported(Port.Info.MICROPHONE)) {
			throw new MicrophoneNotAvailible();
		}
		 format = new AudioFormat(8000.0f, 16, 1, true, true);
	     try {
			microphone = (TargetDataLine) AudioSystem.getLine(new DataLine.Info(TargetDataLine.class, format));
		} catch (LineUnavailableException e) {
			throw new MicrophoneNotAvailible();
		}
	}
	
	
	public static void main(String[] args) throws DataProviderException, IOException, InterruptedException {
		ServerSocket ss = new ServerSocket(13900);
		BufferedMicrophoneDataProvider bufferedMicrophoneDataProvider = new BufferedMicrophoneDataProvider(64);
		InputStream inputStream = bufferedMicrophoneDataProvider.getInputStream();
		byte[] val = new byte[64];
		while(true){
			if(inputStream.read(val) > 0 ) {
				System.out.println(Arrays.toString(val));
			}
		}
	}

	@Override
	public InputStream getInputStream() {
		if(!microphone.isOpen()) {
			try {
				microphone.open(format, bufferSize);
				microphone.start();
			} catch(LineUnavailableException e) {
				//not yet
			}
		}
		return new AudioInputStream(microphone);
	}
}
