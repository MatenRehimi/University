public class ScreenAsBytes {

    public static void setPixel(byte[] screen, int width, int x, int y) {
        
    	int counter = 0;
		
		if (x > 7) {
			x = x-8;
			counter++;
		}
		
        int byteIndex = (width/8)*y+counter;
        
        screen[byteIndex] |= (1 << 7-x);
    }

    
    public static void drawHorizontalLine(byte[] screen, int width, int startX, int endX, int y) {
       
    		for (int i = startX; i <=  endX; i++) {
	        
    			setPixel(screen, width, i, y);
  		
    		}
    }
    
    public static void drawVerticalLine(byte[] screen, int width, int x, int startY, int endY) {
    		
    	for (int i = startY; i <=  endY; i++) {
	        
 	       setPixel(screen,width,x,i);
 		
     	}
    }
}
