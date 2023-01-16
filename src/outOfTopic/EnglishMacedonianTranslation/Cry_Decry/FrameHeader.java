package outOfTopic.EnglishMacedonianTranslation.Cry_Decry;


import java.nio.charset.StandardCharsets;

public class FrameHeader {
	String SourceMAC;
    String DestinationMAC;

    public FrameHeader(String sourceMAC, String destinationMAC) throws Exception {
        SourceMAC = sourceMAC;
        DestinationMAC = destinationMAC;
    }
    
    @Override
    public String toString() {
        return " SrcMaC :" + SourceMAC + "\n" +
               " DestMaC : " + DestinationMAC;
    }

    
    public byte []  getSourceMACAddress() {
        return SourceMAC.getBytes(StandardCharsets.UTF_8);
    }

    
    public byte [] getDestinationMACAddress() {
        return DestinationMAC.getBytes(StandardCharsets.UTF_8);
    }

   
}


