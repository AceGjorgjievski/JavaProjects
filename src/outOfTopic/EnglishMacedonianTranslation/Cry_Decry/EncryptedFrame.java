package outOfTopic.EnglishMacedonianTranslation.Cry_Decry;


import javax.crypto.spec.IvParameterSpec;
import java.util.Arrays;
import java.util.Base64;

public class EncryptedFrame {
	 private byte [] InicijalizirackiV;
	    private byte [] Data;
	    private byte [] encryptedMIC;
	    private byte [] MIC;
	    private FrameHeader Header;
	    IvParameterSpec ivParameterSpec;

	    public byte[] getMIC() {
	        return MIC;
	    }

	    public void setMIC(byte[] MIC) {
	        this.MIC = MIC;
	    }

	    public byte[] getIV() {
	        return InicijalizirackiV;
	    }

	    public void setIV(byte[] IV) {
	        this.InicijalizirackiV = IV;
	    }

	    public FrameHeader getFrameHeader() {
	        return Header;
	    }

	    public void setFrameHeader(FrameHeader frameHeader) {
	        this.Header = frameHeader;
	    }

	    public byte[] getPayloadData() {
	        return Data;
	    }

	    public void setPayloadData(byte[] payloadData) {
	        this.Data = payloadData;
	    }

	    public byte[] getEncryptedMIC() {
	        return encryptedMIC;
	    }

	    public void setEncryptedMIC(byte[] encryptedMIC) {
	        this.encryptedMIC = encryptedMIC;
	    }

	    public IvParameterSpec getIvParameterSpec() {
	        return ivParameterSpec;
	    }

	    public void setIvParameterSpec(IvParameterSpec ivParameterSpec) {
	        this.ivParameterSpec = ivParameterSpec;
	    }

	    @Override
	    public String toString() {
	      StringBuilder sb = new StringBuilder();
	      sb.append(Header.toString() + "\n EncryptedMessage: " + Base64.getEncoder().encodeToString(Data) + "\n MIC: " + Base64.getEncoder().encodeToString(encryptedMIC));
	      return sb.toString();
	    }
}
