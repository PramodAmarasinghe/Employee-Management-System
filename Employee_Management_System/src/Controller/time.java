package Controller;

import java.text.SimpleDateFormat;
import java.util.Date;

public class time {
	
	Date d = new Date();
    SimpleDateFormat st = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss a");
    String str = st.format(d);
    

}
