
import view.AbstractView;
import view.Entry;
import view.LandView;
import view.PlayView;
import view.SideView;
import view.WelcomeView;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import org.apache.commons.lang3.reflect.FieldUtils;

import controller.PlayController;
import controller.WelcomeController;
import dao.AbstractDAO;
import dao.CellDAO;
import dao.FertilizerDAO;
import dao.FlowerDAO;
import dao.PesticideDAO;
import dao.RegisterDAO;
import dao.SettingDAO;
import dao.UserDAO;
import entity.Cell;
import entity.Fertilizer;
import entity.Flower;
import entity.Pesticide;
import entity.Register;
import entity.Setting;
import entity.User;
import factory.ControllerFactory;

@SuppressWarnings("unused")
public class Test {
////	static DashboardView d;
////	public App() {
////		d = new DashboardView();
////	}
////	
////	public static void showDashboardView() {
////		EventQueue.invokeLater(new Runnable() {
////			@Override
////			public void run() {
////				d.showPlotBtn(9);
////				d.setVisible(true);
////				handleEvent();
////			}
////		});
////	}
////	public static void handleEvent() {
////		d.getSettingBtn().addActionListener(e -> {
////			d.setPlotNumLabel("thanhdang");
////			d.showMessage("hello");
////		});
////	}
//	
//
//	
//	
//	static Timer timer;
//
//	public static void main(String[] args) {
////		WelcomeController wc = new WelcomeController();
////		d.showWelcomeView();
//
////		JFrame jf = new JFrame();
////		jf.setLayout(new GridLayout(2, 2, 5, 5));
////		jf.add(new LandView());
////		jf.add(new LandView());
////		jf.add(new LandView());
////		jf.setTitle("Flower Farm – SidePanelView");
////		jf.setSize(860, 580);
////		jf.setLocationRelativeTo(null);
////		jf.setVisible(true);
////		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
////		DashboardController d = new DashboardController("ptdhello", new WelcomeView());
////		d.showDashboardView();
//
//		////////////////////////////
//		
//
////		List<AbstractView> lv = new ArrayList<>();
////		AbstractView pv = new PlayView();
////		lv.add(pv);
////		
////		List<AbstractDAO> ld = new ArrayList<>();
////		ld.add(new UserDAO("ptdhello"));
////		
////		PlayController pc = new PlayController(lv, ld);
////
////		JFrame jf = new JFrame();
////		jf.add((Component) pv);
////		jf.pack();
////		jf.setTitle("Flower Farm – PlayView");
////		jf.setSize(1050, 680);
////		jf.setVisible(true);
////		jf.setLocationRelativeTo(null);
////		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//		//////////////////////////////////////
//
////		boolean b = true;
////		timer = new Timer(1000, new ActionListener() { // Change parameters to your needs.
////			int count = 30;
////			@Override
////			public void actionPerformed(ActionEvent e) {
////				count--;
////				if (count == 0) // They lose
////				{
////					System.out.println(count);
////					System.out.println("You lose");
////					timer.stop(); // ends the countdown.
////				} else if (count == 0 && b == true) {
////					System.out.println(count);
////					System.out.println("You win");
////					timer.stop(); // ends the countdown
////				} else {
////					System.out.println(count);
////				}
////			}
////		});
////		timer.start();
////		
////		timer = new Timer(3000, new ActionListener() {
////			  @Override
////			  public void actionPerformed(ActionEvent arg0) {
////			    System.out.println("Hello");
////			  }
////			});
////		timer.start();
////
////		ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
////
////		Runnable task = new Runnable() {
////			@Override
////			public void run() {
////				System.out.println("Terminate task executed");
////
////			}
////		};
////		executor.schedule(task, 3, TimeUnit.SECONDS);
////		executor.shutdown();
////		
////		try {
////			int i = 5;
////			while (true) {
////				if (i == 0) {
////					return;
////				}
////				System.out.print(i--);
////				Thread.sleep(1000);
////			}
////		} catch (InterruptedException e) {
////			e.printStackTrace();
////		}
//
////		try {
////			int i = 10;
////			while (true) {
////				if (i == 0) {
////					return;
////				}
////				System.out.print(i--);
////				Thread.sleep(1000);
////			}
////		} catch (InterruptedException e) {
////			e.printStackTrace();
////		}
////		System.out.println("Hello");
////		(int) (cell.getFinalRevenue() * (1 + (float) fertilizer.getPercent() / 100)));
////		System.out.println((int) (20 * (1 + (float) 50 / 100)));
//
////		CellDAO cd = new CellDAO("ptdhello", 1);
////		cd.add(new Cell(2));
//
////		String[] s = new String[] {"3", "4"};
////		System.out.println(String.join("-", s));
//
////		new FlowerDAO().getList().forEach(System.out::println);
////		new FertilizerDAO().getList().forEach(System.out::println);
////		new PesticideDAO().getList().forEach(System.out::println);
//
////		App a = new App();
////		showDashboardView();
////		DashboardView d = new DashboardView();
////		d.setHelloLabel("thanhdang");
////		d.setLevelLabel(10);
////		d.setExpLabel(300);
////		d.setCoinLabel(30);
////		d.setPlotNumLabel(12);
////		d.showPlotBtn(9);
////		d.setVisible(true);
////		JLabel ddd = new JLabel("admieeen");
//////		d.setUsernameLabel("thanhdang");
////		d.setUsernameLabel(ddd);
////		d.showPlotBtn(9);
////		d =  new DashboardView();
//////		d.getUsernameLabel().setText("abcsss");
////		d.setVisible(true);
////		JButton[] jb = d.getPlotBtns();
////		for (JButton b : jb) {
////			System.out.println(b.getText());
////		}
//
////		UserDAO u = new UserDAO("hoahuongduong");
////		System.out.println(u.getList().get(0));
////		u.setProperty("exp", 9118822);
////		System.out.println(u.getList().get(0));
////		System.out.println((int) u.getProperty("coin"));
//
////		SettingDAO s = new SettingDAO();
////		System.out.println(s.getProperty("plotNumMax"));
//
////		String path = "user_data/abeeeeeecd/user.csv";
////		System.out.println(path.substring(0, path.length() - 9));
////		System.out.println(u.CSV);
////
////		Register r1 = new Register("admin", "002");
////		System.out.println(r1);
////		User u = new User(r1);
////		System.out.println(u);
////		final List<Field> allFields = FieldUtils.getAllFieldsList(r1.getClass());
////		for (Field e : allFields) {
////			System.out.println(e.getName());
////		}
////		System.out.println(allFields);
////		try {
////			Field field = FieldUtils.getDeclaredField(r1.getClass(), "id");
//////			Field[] field = u.getClass().getDeclaredFields();
////			Object value = FieldUtils.readField(r1, "level", true);
////			System.out.println(value);
////		} catch (SecurityException | IllegalArgumentException | IllegalAccessException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
////		System.out.println(r1.hashCode());
////		User u1 = new User(r1.getId(), r1.getUsername(), r1.getPassword(), 0, 0, 0, 0);
////		System.out.println(r1);
////		System.out.println(u1);
//
////		try {
////			Field field = r1.getClass().getDeclaredField("id");
////			field.setAccessible(true);
////			Object value = field.get(r1);
////			System.out.println((String) value);
////		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
////			e.printStackTrace();
////		}
////		Register r1 = new Register("admin", "002");
////		Register r2 = new Register("hello2", "333");
////		Register r3 = new Register("c3e3439c-d9bd-48c3-8293-9edf0674912a", "h233322e2", "33");
////		registerDAO.add(r1);
////		registerDAO.delete(r3);
////		registerDAO.add(r2);
////		registerDAO.edit(r3);
////		RegisterDAO registerDAO = new RegisterDAO();
////		for (Register register : registerDAO.getRegisterList()) {
////			System.out.println(register);
////		}
//////		registerDAO.delete(r3);
////		List<String> values =  Arrays.asList(new String[]{"123", "abc", "4569"});
////		System.out.println(values);
////		Map<String, String> map = new HashMap<>();
////		map.put("id", "123");
////		map.put("username", "abc");
////		map.put("password", "4569");
////		System.out.println(map.entrySet());
////		for (Entry<String, String> entry : map.entrySet()) {
//////			System.out.println(entry.getKey() + " - " + entry.getValue());
////			Method[] methods = r1.getClass().getMethods();
////			for (Method m : methods) {
////				if(m.getName().endsWith(entry.getKey())) {
////					System.out.println(m.getName());
////				}
////			}
////		}
////		Field[] fields = r1.getClass().getDeclaredFields();
////		for (Field f : fields) {
////			System.out.println(f.getName());
////		}
////		System.out.println("userna");
//	}
}
