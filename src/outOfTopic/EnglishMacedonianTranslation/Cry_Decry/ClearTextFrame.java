package outOfTopic.EnglishMacedonianTranslation.Cry_Decry;


import javax.crypto.spec.IvParameterSpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;

public class ClearTextFrame {

	IvParameterSpec ivParameterSpec;
    FrameHeader Header;
    byte [] Data;
    byte [] InicijalizirackiV = new byte[16];
    byte [] MIC;
    
    
    
    public ClearTextFrame(String payloadData, String SrcMAC, String DestMAC) throws Exception {
        generateInitialVector();
        Header = new FrameHeader(SrcMAC,DestMAC);
        this.Data = payloadData.getBytes(StandardCharsets.UTF_8);
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

    public byte[] getMIC() {
        return MIC;
    }

    public IvParameterSpec getIvParameterSpec() {
        return ivParameterSpec;
    }

    public void setIvParameterSpec(IvParameterSpec ivParameterSpec) {
        this.ivParameterSpec = ivParameterSpec;
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

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(Header.toString());
        sb.append("\n Payload Data: "  + new String(Data,StandardCharsets.UTF_8) +"\n MIC: " + Base64.getEncoder().encodeToString(MIC) );
        return sb.toString();
    }

    public void generateInitialVector(){
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(InicijalizirackiV);
        this.ivParameterSpec = new IvParameterSpec(InicijalizirackiV);
    }

}
