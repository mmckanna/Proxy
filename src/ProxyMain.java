import java.net.*;
import javax.swing.*;
import java.util.*;

public class ProxyMain {
	
	ImageComponent imageComponent;
	JFrame frame = new JFrame("Stormtrooper Viewer");
	JMenuBar menuBar;
	JMenu menu;
	Hashtable<String, String> stormtrooper = new Hashtable<String, String>();

	public static void main (String[] args) throws Exception {
		ProxyMain testDrive = new ProxyMain();
	}

	public ProxyMain() throws Exception{
		stormtrooper.put("Default Stormtrooper","https://images-na.ssl-images-amazon.com/images/I/71llLVCMuQL._SX466_.jpg");
		stormtrooper.put("Deathtrooper","https://s3.amazonaws.com/tf.images/reduced-galery_image_12281_22916.jpg");
		stormtrooper.put("Wet-weather Stormtrooper","https://i.pinimg.com/originals/d0/ef/2d/d0ef2d0974947477b24c6b3bf1e1307c.jpg");
		stormtrooper.put("Imperial Shocktrooper","https://vignette.wikia.nocookie.net/starwars/images/9/94/Imperial_Shock_Trooper_canon.png/revision/latest?cb=20151106024630");
				

		URL initialURL = new URL((String)stormtrooper.get("Deathtrooper"));
		menuBar = new JMenuBar();
		menu = new JMenu("Stormtrooper");
		menuBar.add(menu);
		frame.setJMenuBar(menuBar);

		for (Enumeration<String> e = stormtrooper.keys(); e.hasMoreElements();) {
			String name = (String)e.nextElement();
			JMenuItem menuItem = new JMenuItem(name);
			menu.add(menuItem); 
			menuItem.addActionListener(event -> {
				imageComponent.setIcon(new ImageProxy(getStromtrooperURL(event.getActionCommand())));
				frame.repaint();
			});
		}

		// set up frame and menus

		Icon icon = new ImageProxy(initialURL);
		imageComponent = new ImageComponent(icon);
		frame.getContentPane().add(imageComponent);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800,1100);
		frame.setVisible(true);

	}

	URL getStromtrooperURL(String name) {
		try {
			return new URL((String)stormtrooper.get(name));
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return null;
		}
	}

}
