package applikasi;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.LocalDate;
import java.awt.Toolkit;
import javax.swing.UIManager;

public class menuLogin extends JFrame {

	public String name;
	public String password;
	public String accname;
	public String accpassword;
	private int akun;

	private static final long serialVersionUID = 1L;

	ArrayList<Account> Accounts = new ArrayList<Account>();
	ArrayList<Film> pilm = new ArrayList<Film>();
	private JPasswordField InptPass;
	private JTextField tfSaldo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					menuLogin window = new menuLogin();
					window.MenuLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public menuLogin() {
		initialize();
	}

	@SuppressWarnings("deprecation")
	public void initialize() {
		LocalDate todayDate = LocalDate.now();
		// aaa
		pilm.add(new Film("Avenger : End Game", 60000));

		pilm.add(new Film("Life Of Pi", 45000));

		pilm.add(new Film("Josse : Tiger And Fish", 40000));

		pilm.add(new Film("Danur", 20000));

		pilm.add(new Film("Raya and the Last Dragon", 35000));

		for (int uu = 0; uu < pilm.size(); uu++) {
			for (int i = 0; i < 4; i++) {
				String tanggal = String.valueOf(besok(todayDate, i));
				pilm.get(uu).setTanggal(tanggal);
			}
		}

		// Menambahkan AkunAdmin
//		int reply = JOptionPane.showConfirmDialog(null, "Apakah Anda Ingin Menambahkan Akun Admin?", "",
//				JOptionPane.YES_NO_OPTION);
//		if (reply == JOptionPane.YES_OPTION) {
//			Accounts.add(new Account("Admin", "Admin", 1000000));
//			JOptionPane.showMessageDialog(null, "Akun Admin Ditambahkan");
//
//		}

		// Window Login
		MenuLogin = new JFrame("TiketGui.Com");
		MenuLogin.setIconImage(
				Toolkit.getDefaultToolkit().getImage(menuLogin.class.getResource("/images/logo Aplikasi.png")));
		MenuLogin.setBounds(100, 100, 720, 480);
		MenuLogin.setResizable(false);
		MenuLogin.getContentPane().setBackground(Color.LIGHT_GRAY);
		MenuLogin.getContentPane().setLayout(null);

		JPanel Header = new JPanel();
		Header.setBounds(0, 0, 0, 0);
		MenuLogin.getContentPane().add(Header);
		MenuLogin.setBounds(100, 100, 720, 480);
		MenuLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		InptNama = new JTextField();
		InptNama.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char a = e.getKeyChar();
				String c = String.valueOf(a);
				if (!(c.matches("(.*)[a-zA-Z ](.*)")) && (c != "\b")) {
					e.consume();
				}
			}
		});
		InptNama.setColumns(10);
		InptNama.setBounds(244, 202, 209, 27);
		InptNama.setText("");
		MenuLogin.getContentPane().add(InptNama);

		JButton TmblLogin = new JButton("Login");
		TmblLogin.setFont(new Font("Tahoma", Font.PLAIN, 15));
		TmblLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = InptNama.getText();
				String password = InptPass.getText().toString();
				if (Accounts.size() > 0) {
					if (!(name.equals("")) || !(password.equals(""))) {

						boolean check = false;
						for (int i = 0; i < Accounts.size(); i++) {
							Account tempAccounts = (Account) Accounts.get(i);
							if ((tempAccounts.accname.equals(name)) && (tempAccounts.accpassword.equals(password))) {
								MenuLogin.setVisible(false);
								MainMenu.setVisible(true);
								akun = i;
								refreshAkun();
								refreshTable();
								check = true;
							}
						}
						if (check == false) {
							JOptionPane.showMessageDialog(null, "Nama Atau Password Salah");
						}

					} else {
						JOptionPane.showMessageDialog(null, "Nama Dan Passowrd Harus Diisi");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Belum Ada Akun Yang Dibuat");
				}

			}

		});
		TmblLogin.setBounds(283, 306, 111, 38);
		MenuLogin.getContentPane().add(TmblLogin);

		JButton TmblRegister = new JButton("Register");
		TmblRegister.setFont(new Font("Tahoma", Font.PLAIN, 15));
		TmblRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = InptNama.getText();
				String password = InptPass.getText();
				Boolean check = true;
				if (!(name.equals("")) || !(password.equals(""))) {

					for (int i = 0; i < (Accounts.size()); i++) {
						Account tempAccounts = (Account) Accounts.get(i);
						if (tempAccounts.getAccname().equals(name)) {
							JOptionPane.showMessageDialog(null, "Nama Telah Digunakan");
							check = false;
						}
					}

					if (check == true) {
						Accounts.add(new Account(name, password, 0));
						JOptionPane.showMessageDialog(null, "Account Dibuat");
						InptNama.setText("");
						InptPass.setText("");
					}

				} else {
					JOptionPane.showMessageDialog(null, "Nama Dan Passowrd Harus Diisi");
				}
			}
		});
		TmblRegister.setBounds(283, 362, 111, 38);
		MenuLogin.getContentPane().add(TmblRegister);

		JLabel lblLoginNama = new JLabel("Nama     :");
		lblLoginNama.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblLoginNama.setBounds(156, 206, 78, 14);
		MenuLogin.getContentPane().add(lblLoginNama);

		JLabel lblPassowrd = new JLabel("Passowrd :");
		lblPassowrd.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPassowrd.setBounds(156, 260, 78, 14);
		MenuLogin.getContentPane().add(lblPassowrd);

		JPanel pnlLogo = new JPanel();
		pnlLogo.setBackground(Color.WHITE);
		pnlLogo.setBounds(0, 0, 704, 166);
		MenuLogin.getContentPane().add(pnlLogo);
		pnlLogo.setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(menuLogin.class.getResource("/images/logo Aplikasi.png")));
		lblNewLabel.setBounds(178, 31, 330, 89);
		pnlLogo.add(lblNewLabel);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));

		InptPass = new JPasswordField();
		InptPass.setBounds(244, 256, 209, 27);
		MenuLogin.getContentPane().add(InptPass);

		JPanel pnlLogin = new JPanel();
		pnlLogin.setBounds(0, 166, 704, 275);
		MenuLogin.getContentPane().add(pnlLogin);

		// Window MainMenu
		MainMenu = new JFrame();
		MainMenu.setIconImage(
				Toolkit.getDefaultToolkit().getImage(menuLogin.class.getResource("/images/logo Aplikasi.png")));
		MainMenu.setBounds(100, 100, 720, 480);
		MainMenu.setResizable(false);
		MainMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MainMenu.getContentPane().setLayout(null);

		JButton btnback1 = new JButton("Log Out");
		btnback1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int reply = JOptionPane.showConfirmDialog(null, "Apakah Anda Yakin Ingin Logout?", "",
						JOptionPane.YES_NO_OPTION);
				if (reply == JOptionPane.YES_OPTION) {
					JOptionPane.showMessageDialog(null, "Logout Berhasil");
					MenuLogin.setVisible(true);
					MainMenu.setVisible(false);
					akun = 0;
				} else {
					JOptionPane.showMessageDialog(null, "Logout Dibatalkan");
				}

			}
		});
		btnback1.setBounds(10, 11, 104, 23);
		MainMenu.getContentPane().add(btnback1);

		JPanel panelMenu1 = new JPanel();
		panelMenu1.setBackground(Color.WHITE);
		panelMenu1.setBounds(73, 98, 561, 317);
		MainMenu.getContentPane().add(panelMenu1);
		panelMenu1.setLayout(null);

		JLabel lblDaftarTiket = new JLabel("Daftar Tiket Kamu :");
		lblDaftarTiket.setBounds(20, 36, 109, 14);
		panelMenu1.add(lblDaftarTiket);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 52, 521, 186);
		panelMenu1.add(scrollPane);

		TabelModel = new DefaultTableModel();
		TabelModel.addColumn("No.");
		TabelModel.addColumn("Nama Film");
		TabelModel.addColumn("Jam");
		TabelModel.addColumn("Tanggal");
		TabelModel.addColumn("Bangku");
		TabelModel.addColumn("Jmlh");
		table = new JTable();
		table.setModel(TabelModel);
		table.getColumnModel().getColumn(0).setPreferredWidth(47);
		table.getColumnModel().getColumn(1).setPreferredWidth(150);
		table.getColumnModel().getColumn(2).setPreferredWidth(80);
		table.getColumnModel().getColumn(4).setPreferredWidth(47);
		scrollPane.setViewportView(table);

		JLabel lebelNamaMenu1 = new JLabel("Nama Akun : ");
		lebelNamaMenu1.setBounds(20, 11, 98, 14);
		panelMenu1.add(lebelNamaMenu1);

		taMenu1 = new JTextArea();
		taMenu1.setBackground(UIManager.getColor("Button.shadow"));
		taMenu1.setEditable(false);
		taMenu1.setBounds(89, 10, 157, 17);
		panelMenu1.add(taMenu1);

		JButton btnGunakanTiket = new JButton("Gunakan Tiket");
		btnGunakanTiket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() >= 0) {

					int reply = JOptionPane.showConfirmDialog(null,
							"Apakah Anda Yakin Ingin menggunakan tiket ini?" + "\nFilm : "
									+ Accounts.get(akun).tikets.get(table.getSelectedRow()).namaF + "\nJam : "
									+ Accounts.get(akun).tikets.get(table.getSelectedRow()).waktu + "\nBangku : "
									+ Accounts.get(akun).tikets.get(table.getSelectedRow()).bangku,
							"", JOptionPane.YES_NO_OPTION);
					if (reply == JOptionPane.YES_OPTION) {
						Accounts.get(akun).tikets.remove((table.getSelectedRow()));
						JOptionPane.showMessageDialog(null, "Tiket Digunakan");
					} else {
						JOptionPane.showMessageDialog(null, "Dibatalkan");
					}

					refreshTable();
				} else {
					JOptionPane.showMessageDialog(null, "Piih Tiket Terlebih Dahulu");
				}

			}
		});
		btnGunakanTiket.setBounds(222, 249, 128, 23);
		panelMenu1.add(btnGunakanTiket);

		lblJumlahtiket = new JLabel("Jumlah Tiket :");
		lblJumlahtiket.setBounds(20, 249, 123, 23);
		panelMenu1.add(lblJumlahtiket);

		JButton btnKeMenu2 = new JButton("Beli Tiket");
		btnKeMenu2.setBounds(341, 283, 109, 23);
		panelMenu1.add(btnKeMenu2);

		JLabel lblSaldo = new JLabel("Saldo Anda");
		lblSaldo.setBounds(333, 11, 59, 14);
		panelMenu1.add(lblSaldo);

		tfSaldo = new JTextField();
		tfSaldo.setBackground(UIManager.getColor("Button.shadow"));
		tfSaldo.setEditable(false);
		tfSaldo.setBounds(402, 11, 123, 17);
		panelMenu1.add(tfSaldo);
		tfSaldo.setColumns(10);

		JButton btnKeMenuTopUp = new JButton("Top Up Saldo");
		btnKeMenuTopUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainMenu.setVisible(false);
				MenuTopUp.setVisible(true);
			}
		});
		btnKeMenuTopUp.setBounds(111, 283, 117, 23);
		panelMenu1.add(btnKeMenuTopUp);
		btnKeMenu2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuTiket.setVisible(true);
				MainMenu.setVisible(false);
			}
		});

		JLabel logoMenu1 = new JLabel("");
		logoMenu1.setIcon(new ImageIcon(menuLogin.class.getResource("/images/logo Aplikasi.png")));
		logoMenu1.setBounds(180, 11, 330, 89);
		MainMenu.getContentPane().add(logoMenu1);

		// Window Top Up
		MenuTopUp = new JFrame();
		MenuTopUp.setResizable(false);
		MenuTopUp.setIconImage(
				Toolkit.getDefaultToolkit().getImage(menuLogin.class.getResource("/images/logo Aplikasi.png")));
		MenuTopUp.setBounds(100, 100, 720, 480);
		MenuTopUp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MenuTopUp.getContentPane().setLayout(null);

		JLabel lblLogoTp = new JLabel("");
		lblLogoTp.setIcon(new ImageIcon(menuLogin.class.getResource("/images/logo Aplikasi.png")));
		lblLogoTp.setBounds(172, 11, 330, 89);
		MenuTopUp.getContentPane().add(lblLogoTp);

		JPanel pnlTopUp = new JPanel();
		pnlTopUp.setBackground(UIManager.getColor("Button.light"));
		pnlTopUp.setBounds(136, 104, 393, 281);
		MenuTopUp.getContentPane().add(pnlTopUp);
		pnlTopUp.setLayout(null);

		JButton btnKembaliTopup = new JButton("");
		btnKembaliTopup.setIcon(new ImageIcon(menuLogin.class.getResource("/images/Untitled design.png")));
		btnKembaliTopup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainMenu.setVisible(true);
				MenuTopUp.setVisible(false);
				refreshAkun();
			}
		});
		btnKembaliTopup.setBounds(23, 11, 67, 40);
		MenuTopUp.getContentPane().add(btnKembaliTopup);

		taSaldoTp = new JTextArea();
		taSaldoTp.setBackground(UIManager.getColor("Button.shadow"));
		taSaldoTp.setEditable(false);
		taSaldoTp.setBounds(125, 104, 190, 22);
		pnlTopUp.add(taSaldoTp);

		taNamaTp = new JTextArea();
		taNamaTp.setBackground(UIManager.getColor("Button.shadow"));
		taNamaTp.setEditable(false);
		taNamaTp.setBounds(125, 71, 190, 22);
		pnlTopUp.add(taNamaTp);

		JLabel lblTopUp = new JLabel("Top Up");
		lblTopUp.setBounds(70, 0, 225, 54);
		pnlTopUp.add(lblTopUp);
		lblTopUp.setFont(new Font("Tahoma", Font.BOLD, 35));
		lblTopUp.setHorizontalAlignment(SwingConstants.CENTER);

		JButton btnTopUp = new JButton("Top Up");
		btnTopUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if ((Integer.valueOf(tfTopUp.getText()) >= 50000)) {
						int h = Accounts.get(akun).saldo + (Integer.valueOf(tfTopUp.getText()));

						int reply = JOptionPane.showConfirmDialog(null,
								"Apakah Anda Yakin Top Up Sejumlah " + tfTopUp.getText() + "?", "",
								JOptionPane.YES_NO_OPTION);
						if (reply == JOptionPane.YES_OPTION) {
							Accounts.get(akun).setSaldo(h);
							JOptionPane.showMessageDialog(null, "Berhasil Top Up");
						} else {
							JOptionPane.showMessageDialog(null, "Top Up Dibatalkan");
						}
						tfTopUp.setText("");
						
						
					} else {
						JOptionPane.showMessageDialog(null, "Jumlah Top Up Minimal 50.000");
					}

					refreshAkun();
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Masukan jumlah Sewajarnya");
				}

			}
		});
		btnTopUp.setBounds(175, 197, 89, 23);
		pnlTopUp.add(btnTopUp);

		tfTopUp = new JTextField();
		tfTopUp.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char a = e.getKeyChar();
				String c = String.valueOf(a);
				if (!(c.matches("(.*)[0-9](.*)")) && (c != "\b")) {
					e.consume();
				}
			}
		});
		tfTopUp.setBounds(168, 166, 147, 20);
		pnlTopUp.add(tfTopUp);
		tfTopUp.setColumns(10);

		JLabel lblNamaTp = new JLabel("Nama Akun");
		lblNamaTp.setBounds(24, 76, 68, 14);
		pnlTopUp.add(lblNamaTp);

		JLabel lblJmlSaldoTp = new JLabel("Saldo Anda");
		lblJmlSaldoTp.setBounds(24, 109, 68, 14);
		pnlTopUp.add(lblJmlSaldoTp);

		JLabel lblJumlahTp = new JLabel("Masukan Nominal");
		lblJumlahTp.setBounds(24, 169, 115, 14);
		pnlTopUp.add(lblJumlahTp);

		// Window Pembelian Tiket
		menuTiket = new JFrame();
		menuTiket.setTitle("Pembelian Tiket");
		menuTiket.setIconImage(
				Toolkit.getDefaultToolkit().getImage(menuLogin.class.getResource("/images/logo Aplikasi.png")));
		menuTiket.setResizable(false);
		menuTiket.setBounds(100, 100, 720, 480);
		menuTiket.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menuTiket.getContentPane().setLayout(null);
		menuTiket.setBounds(100, 100, 720, 480);
		menuTiket.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JButton kembali2 = new JButton("");
		kembali2.setIcon(new ImageIcon(menuLogin.class.getResource("/images/Untitled design.png")));
		kembali2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				pilWaktu.setSelectedItem("15:00");
				pilTanggal.setSelectedItem(String.valueOf(todayDate));

				pilWaktu.setEnabled(false);
				pilTanggal.setEnabled(false);
				tiketBeli.setEnabled(false);
				refreshAkun();
				MainMenu.setVisible(true);
				menuTiket.setVisible(false);
			}
		});
		kembali2.setBounds(23, 11, 67, 40);
		menuTiket.getContentPane().add(kembali2);

		JLabel gambarFilm = new JLabel("");
		gambarFilm.setIcon(new ImageIcon(menuLogin.class.getResource("/images/Avenger End Game.png")));

		gambarFilm.setBounds(412, 131, 189, 267);
		menuTiket.getContentPane().add(gambarFilm);

		JLabel logoMTiket = new JLabel("");
		logoMTiket.setIcon(new ImageIcon(menuLogin.class.getResource("/images/logo Aplikasi.png")));
		logoMTiket.setBounds(189, 11, 330, 89);
		menuTiket.getContentPane().add(logoMTiket);

		JPanel pnlBelitiket = new JPanel();
		pnlBelitiket.setBackground(UIManager.getColor("CheckBox.light"));
		pnlBelitiket.setBounds(43, 149, 305, 249);
		menuTiket.getContentPane().add(pnlBelitiket);
		pnlBelitiket.setLayout(null);

		pilFilm = new JComboBox<String>();
		pilFilm.setBounds(104, 30, 156, 22);

		List<String> a = new ArrayList<String>();
		for (int ii = 0; ii < pilm.size(); ii++) {
			a.add(pilm.get(ii).namaF);
		}

		String[] namFilm = new String[a.size()];
		a.toArray(namFilm);

		pnlBelitiket.add(pilFilm);
		pilFilm.setModel(new javax.swing.DefaultComboBoxModel<>(namFilm));

		JLabel lblNewLabel_2 = new JLabel("Nama Film");
		lblNewLabel_2.setBounds(29, 34, 63, 14);
		pnlBelitiket.add(lblNewLabel_2);

		JLabel lblTanggal = new JLabel("Tanggal");
		lblTanggal.setBounds(29, 93, 46, 14);
		pnlBelitiket.add(lblTanggal);

		pilTanggal = new JComboBox<String>();
		pilTanggal.setBounds(104, 89, 156, 22);
		pnlBelitiket.add(pilTanggal);
		pilTanggal.setEnabled(false);

		JLabel lblNewLabel_3 = new JLabel("Jam");
		lblNewLabel_3.setBounds(29, 126, 46, 14);
		pnlBelitiket.add(lblNewLabel_3);

		pilWaktu = new JComboBox<String>();
		pilWaktu.setBounds(104, 122, 156, 22);
		pnlBelitiket.add(pilWaktu);
		pilWaktu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tiketBeli.setEnabled(true);
				btnPilihBangku.setEnabled(true);
			}
		});
		pilWaktu.setEnabled(false);
		pilWaktu.setEditable(false);

		tiketBeli = new JButton("Beli Tiket");
		tiketBeli.setBounds(100, 215, 102, 23);
		pnlBelitiket.add(tiketBeli);
		tiketBeli.setEnabled(false);

		JLabel lblHarga = new JLabel("Harga Tiket");
		lblHarga.setBounds(29, 59, 63, 14);
		pnlBelitiket.add(lblHarga);

		JTextArea taHarga = new JTextArea();
		taHarga.setEditable(false);
		taHarga.setBounds(104, 58, 156, 22);
		pnlBelitiket.add(taHarga);

		JLabel lblSaldoo = new JLabel("Saldo Anda");
		lblSaldoo.setBounds(29, 187, 78, 14);
		pnlBelitiket.add(lblSaldoo);

		taSaldoo = new JTextArea();
		taSaldoo.setEditable(false);
		taSaldoo.setBounds(104, 182, 156, 22);
		pnlBelitiket.add(taSaldoo);

		btnPilihBangku = new JButton("Pilih Bangku");
		btnPilihBangku.setEnabled(false);
		btnPilihBangku.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuPilihBangku.setVisible(true);
				menuTiket.setVisible(false);
				refresh();
			}
		});
		btnPilihBangku.setBounds(104, 155, 156, 23);
		pnlBelitiket.add(btnPilihBangku);

		tiketBeli.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (jumlaah() > 0) {
					String Waktu = pilWaktu.getSelectedItem().toString();
					String Film = pilFilm.getSelectedItem().toString();
					String Tanggal = pilTanggal.getSelectedItem().toString();
					int jumlah = jumlaah();
					int harga = Integer.valueOf(taHarga.getText()) * jumlah;

					int saldo = Accounts.get(akun).getSaldo();
					if (saldo >= harga) {
						int reply = JOptionPane.showConfirmDialog(null,
								"Nama Film : " + Film + "\nTanggal : " + Tanggal + "\nWaktu :" + Waktu + "\nTiket :"
										+ tiketTerpilih() + "\nJumlah :" + jumlaah(),
								"Apakah Anda Yakin Ingin Membeli Tiket?", JOptionPane.YES_NO_OPTION);

						if (reply == JOptionPane.YES_OPTION) {
							((Account) Accounts.get(akun)).setAcctiket(Film, Waktu, Tanggal, tiketTerpilih(), jumlah);
							Accounts.get(akun).setSaldo(saldo - harga);
							Beli();
							JOptionPane.showMessageDialog(null, "Tiket Dibeli");

							pilWaktu.setSelectedItem("15:00");
							pilTanggal.setSelectedItem(String.valueOf(todayDate));
							pilWaktu.setEnabled(false);
							pilTanggal.setEnabled(false);
							tiketBeli.setEnabled(false);
							refreshAkun();
							MainMenu.setVisible(true);
							menuTiket.setVisible(false);

						} else {
							JOptionPane.showMessageDialog(null, "Dibatalkan");
						}

					} else {
						JOptionPane.showMessageDialog(null, "Saldo tidak Cukup, Harap Isi Saldo Terlebih Dahulu");
					}
				} else {
					JOptionPane.showMessageDialog(null,
							"Anda Belum Memilih Bangku, Silahkan Memilih Bangku terlebih Dahulu");
				}

				refreshTable();
			}
		});
		pilTanggal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<String> c = new ArrayList<String>();
				for (int ii = 0; ii < pilm.get(pilFilm.getSelectedIndex()).tanggal
						.get(pilTanggal.getSelectedIndex()).waktu.size(); ii++) {
					c.add(pilm.get(pilFilm.getSelectedIndex()).tanggal.get(pilTanggal.getSelectedIndex()).waktu
							.get(ii).jam);
				}

				String[] waktuFilm = new String[c.size()];
				c.toArray(waktuFilm);

				pilWaktu.setModel(new javax.swing.DefaultComboBoxModel<>(waktuFilm));
				pilWaktu.setEnabled(true);
				tiketBeli.setEnabled(true);

			}
		});
		pilFilm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int harga = 0;
				if (pilFilm.getSelectedItem() == "Avenger : End Game") {
					gambarFilm.setIcon(new ImageIcon(menuLogin.class.getResource("/images/Avenger End Game.png")));
					harga = pilm.get(pilFilm.getSelectedIndex()).harga;
				}

				else if (pilFilm.getSelectedItem() == "Life Of Pi") {
					gambarFilm.setIcon(new ImageIcon(menuLogin.class.getResource("/images/Life Of Pi.png")));
					harga = pilm.get(pilFilm.getSelectedIndex()).harga;
				}

				else if (pilFilm.getSelectedItem() == "Josse : Tiger And Fish") {
					gambarFilm.setIcon(new ImageIcon(menuLogin.class.getResource("/images/Josse Tiger And Fish.png")));
					harga = pilm.get(pilFilm.getSelectedIndex()).harga;
				}

				else if (pilFilm.getSelectedItem() == "Danur") {
					gambarFilm.setIcon(new ImageIcon(menuLogin.class.getResource("/images/Danur.png")));
					harga = pilm.get(pilFilm.getSelectedIndex()).harga;
				}

				else if (pilFilm.getSelectedItem() == "Raya and the Last Dragon") {
					gambarFilm.setIcon(
							new ImageIcon(menuLogin.class.getResource("/images/Raya and the Last Dragon.png")));
					harga = pilm.get(pilFilm.getSelectedIndex()).harga;
				}

				else {
					gambarFilm.setIcon(null);
				}

				List<String> c = new ArrayList<String>();
				for (int ii = 0; ii < pilm.get(pilFilm.getSelectedIndex()).tanggal.size(); ii++) {
					c.add(pilm.get(pilFilm.getSelectedIndex()).tanggal.get(ii).tanggal);
				}

				String[] tanggalFilm = new String[c.size()];
				c.toArray(tanggalFilm);

				pilTanggal.setModel(new javax.swing.DefaultComboBoxModel<>(tanggalFilm));

				taHarga.setText(String.valueOf(harga));

				pilTanggal.setEnabled(true);
			}
		});

		// Window Pilih Bangku

		MenuPilihBangku = new JFrame();
		MenuPilihBangku.setBounds(100, 100, 720, 480);
		MenuPilihBangku.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MenuPilihBangku.getContentPane().setLayout(null);

		JLabel uwu = new JLabel("Pilih Bangku");
		uwu.setFont(new Font("Tahoma", Font.BOLD, 30));
		uwu.setHorizontalAlignment(SwingConstants.CENTER);
		uwu.setBounds(89, 49, 501, 70);
		MenuPilihBangku.getContentPane().add(uwu);

		rdn1 = new JRadioButton("1");
		rdn1.setEnabled(false);
		rdn1.setBounds(78, 152, 45, 23);
		MenuPilihBangku.getContentPane().add(rdn1);

		rdn2 = new JRadioButton("2");
		rdn2.setEnabled(false);
		rdn2.setBounds(79, 185, 45, 23);
		MenuPilihBangku.getContentPane().add(rdn2);

		rdn3 = new JRadioButton("3");
		rdn3.setEnabled(false);
		rdn3.setBounds(79, 227, 45, 23);
		MenuPilihBangku.getContentPane().add(rdn3);

		JButton btnPilih = new JButton("Pilih Bangku");
		btnPilih.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuTiket.setVisible(true);
				MenuPilihBangku.setVisible(false);
				tiketBeli.setEnabled(true);
			}
		});
		btnPilih.setBounds(288, 327, 89, 23);
		MenuPilihBangku.getContentPane().add(btnPilih);

		rdn4 = new JRadioButton("4");
		rdn4.setEnabled(false);
		rdn4.setBounds(149, 152, 45, 23);
		MenuPilihBangku.getContentPane().add(rdn4);

		rdn5 = new JRadioButton("5");
		rdn5.setEnabled(false);
		rdn5.setBounds(149, 185, 45, 23);
		MenuPilihBangku.getContentPane().add(rdn5);

		rdn6 = new JRadioButton("6");
		rdn6.setEnabled(false);
		rdn6.setBounds(149, 227, 45, 23);
		MenuPilihBangku.getContentPane().add(rdn6);

		rdn7 = new JRadioButton("7");
		rdn7.setEnabled(false);
		rdn7.setBounds(218, 152, 45, 23);
		MenuPilihBangku.getContentPane().add(rdn7);

		rdn8 = new JRadioButton("8");
		rdn8.setEnabled(false);
		rdn8.setBounds(218, 185, 45, 23);
		MenuPilihBangku.getContentPane().add(rdn8);

		rdn9 = new JRadioButton("9");
		rdn9.setEnabled(false);
		rdn9.setBounds(218, 227, 45, 23);
		MenuPilihBangku.getContentPane().add(rdn9);

		rdn10 = new JRadioButton("10");
		rdn10.setEnabled(false);
		rdn10.setBounds(413, 152, 45, 23);
		MenuPilihBangku.getContentPane().add(rdn10);

		rdn11 = new JRadioButton("11");
		rdn11.setEnabled(false);
		rdn11.setBounds(413, 185, 45, 23);
		MenuPilihBangku.getContentPane().add(rdn11);

		rdn12 = new JRadioButton("12");
		rdn12.setEnabled(false);
		rdn12.setBounds(413, 227, 45, 23);
		MenuPilihBangku.getContentPane().add(rdn12);

		rdn13 = new JRadioButton("13");
		rdn13.setEnabled(false);
		rdn13.setBounds(486, 152, 45, 23);
		MenuPilihBangku.getContentPane().add(rdn13);

		rdn14 = new JRadioButton("14");
		rdn14.setEnabled(false);
		rdn14.setBounds(486, 185, 45, 23);
		MenuPilihBangku.getContentPane().add(rdn14);

		rdn15 = new JRadioButton("15");
		rdn15.setEnabled(false);
		rdn15.setBounds(486, 227, 45, 23);
		MenuPilihBangku.getContentPane().add(rdn15);

		rdn16 = new JRadioButton("16");
		rdn16.setEnabled(false);
		rdn16.setBounds(564, 152, 45, 23);
		MenuPilihBangku.getContentPane().add(rdn16);

		rdn17 = new JRadioButton("17");
		rdn17.setEnabled(false);
		rdn17.setBounds(564, 185, 45, 23);
		MenuPilihBangku.getContentPane().add(rdn17);

		rdn18 = new JRadioButton("18");
		rdn18.setEnabled(false);
		rdn18.setBounds(564, 227, 45, 23);
		MenuPilihBangku.getContentPane().add(rdn18);
	}

	public void refreshTable() {

		int jmlTiket = 0;
		TabelModel.setRowCount(0);
		for (int i = 0; i < Accounts.get(akun).tikets.size(); i++) {
			String namaFilm = Accounts.get(akun).tikets.get(i).namaF;
			String waktu = Accounts.get(akun).tikets.get(i).waktu;
			String tanggal = Accounts.get(akun).tikets.get(i).tanggal;
			String bangku = Accounts.get(akun).tikets.get(i).bangku;
			String Jumlah = String.valueOf(Accounts.get(akun).tikets.get(i).Jumlah);
			Object[] data = { 1 + i, namaFilm, waktu, tanggal, bangku, Jumlah };
			TabelModel.addRow(data);
			jmlTiket++;
		}
		lblJumlahtiket.setText("Jumlah Tiket : " + jmlTiket);

	}

	public void refreshAkun() {
		taMenu1.setText(Accounts.get(akun).accname);
		tfSaldo.setText(String.valueOf(Accounts.get(akun).saldo));
		taNamaTp.setText(Accounts.get(akun).accname);
		taSaldoTp.setText(String.valueOf(Accounts.get(akun).saldo));
		taSaldoo.setText(String.valueOf(Accounts.get(akun).saldo));

	}

	private static LocalDate besok(LocalDate localdate, int i) {
		return localdate.plusDays(i);
	}

	public void Beli() {
		int a = pilFilm.getSelectedIndex();
		int b = pilWaktu.getSelectedIndex();
		int c = pilTanggal.getSelectedIndex();
		if (rdn1.isSelected()) {
			pilm.get(a).tanggal.get(c).waktu.get(b).bangku.get(0).setDigunakan(true);
		}
		if (rdn2.isSelected()) {
			pilm.get(a).tanggal.get(c).waktu.get(b).bangku.get(1).setDigunakan(true);
		}
		if (rdn3.isSelected()) {
			pilm.get(a).tanggal.get(c).waktu.get(b).bangku.get(2).setDigunakan(true);
		}
		if (rdn4.isSelected()) {
			pilm.get(a).tanggal.get(c).waktu.get(b).bangku.get(3).setDigunakan(true);
		}
		if (rdn5.isSelected()) {
			pilm.get(a).tanggal.get(c).waktu.get(b).bangku.get(4).setDigunakan(true);
		}
		if (rdn6.isSelected()) {
			pilm.get(a).tanggal.get(c).waktu.get(b).bangku.get(5).setDigunakan(true);
		}
		if (rdn7.isSelected()) {
			pilm.get(a).tanggal.get(c).waktu.get(b).bangku.get(6).setDigunakan(true);
		}
		if (rdn8.isSelected()) {
			pilm.get(a).tanggal.get(c).waktu.get(b).bangku.get(7).setDigunakan(true);
		}
		if (rdn9.isSelected()) {
			pilm.get(a).tanggal.get(c).waktu.get(b).bangku.get(8).setDigunakan(true);
		}
		if (rdn10.isSelected()) {
			pilm.get(a).tanggal.get(c).waktu.get(b).bangku.get(9).setDigunakan(true);
		}
		if (rdn11.isSelected()) {
			pilm.get(a).tanggal.get(c).waktu.get(b).bangku.get(10).setDigunakan(true);
		}
		if (rdn12.isSelected()) {
			pilm.get(a).tanggal.get(c).waktu.get(b).bangku.get(11).setDigunakan(true);
		}
		if (rdn13.isSelected()) {
			pilm.get(a).tanggal.get(c).waktu.get(b).bangku.get(12).setDigunakan(true);
		}
		if (rdn14.isSelected()) {
			pilm.get(a).tanggal.get(c).waktu.get(b).bangku.get(13).setDigunakan(true);
		}
		if (rdn15.isSelected()) {
			pilm.get(a).tanggal.get(c).waktu.get(b).bangku.get(14).setDigunakan(true);
		}
		if (rdn16.isSelected()) {
			pilm.get(a).tanggal.get(c).waktu.get(b).bangku.get(15).setDigunakan(true);
		}
		if (rdn17.isSelected()) {
			pilm.get(a).tanggal.get(c).waktu.get(b).bangku.get(16).setDigunakan(true);
		}
		if (rdn18.isSelected()) {
			pilm.get(a).tanggal.get(c).waktu.get(b).bangku.get(17).setDigunakan(true);
		}
	}

	public int jumlaah() {
		int jumlaaah = 0;
		if (rdn1.isSelected()) {
			jumlaaah = jumlaaah + 1;
		}
		if (rdn2.isSelected()) {
			jumlaaah = jumlaaah + 1;
		}
		if (rdn3.isSelected()) {
			jumlaaah = jumlaaah + 1;
		}
		if (rdn4.isSelected()) {
			jumlaaah = jumlaaah + 1;
		}
		if (rdn5.isSelected()) {
			jumlaaah = jumlaaah + 1;
		}
		if (rdn6.isSelected()) {
			jumlaaah = jumlaaah + 1;
		}
		if (rdn7.isSelected()) {
			jumlaaah = jumlaaah + 1;
		}
		if (rdn8.isSelected()) {
			jumlaaah = jumlaaah + 1;
		}
		if (rdn9.isSelected()) {
			jumlaaah = jumlaaah + 1;
		}
		if (rdn10.isSelected()) {
			jumlaaah = jumlaaah + 1;
		}
		if (rdn11.isSelected()) {
			jumlaaah = jumlaaah + 1;
		}
		if (rdn12.isSelected()) {
			jumlaaah = jumlaaah + 1;
		}
		if (rdn13.isSelected()) {
			jumlaaah = jumlaaah + 1;
		}
		if (rdn14.isSelected()) {
			jumlaaah = jumlaaah + 1;
		}
		if (rdn15.isSelected()) {
			jumlaaah = jumlaaah + 1;
		}
		if (rdn16.isSelected()) {
			jumlaaah = jumlaaah + 1;
		}
		if (rdn17.isSelected()) {
			jumlaaah = jumlaaah + 1;
		}
		if (rdn18.isSelected()) {
			jumlaaah = jumlaaah + 1;
		}
		return jumlaaah;
	}

	public String tiketTerpilih() {
		int a = pilFilm.getSelectedIndex();
		int b = pilWaktu.getSelectedIndex();
		int c = pilTanggal.getSelectedIndex();
		String noBangku = "";
		if (rdn1.isSelected()) {
			noBangku = noBangku + pilm.get(a).tanggal.get(c).waktu.get(b).bangku.get(0).codeB + ",";
		}
		if (rdn2.isSelected()) {
			noBangku = noBangku + pilm.get(a).tanggal.get(c).waktu.get(b).bangku.get(1).codeB + ",";
		}
		if (rdn3.isSelected()) {
			noBangku = noBangku + pilm.get(a).tanggal.get(c).waktu.get(b).bangku.get(2).codeB + ",";
		}
		if (rdn4.isSelected()) {
			noBangku = noBangku + pilm.get(a).tanggal.get(c).waktu.get(b).bangku.get(3).codeB + ",";
		}
		if (rdn5.isSelected()) {
			noBangku = noBangku + pilm.get(a).tanggal.get(c).waktu.get(b).bangku.get(4).codeB + ",";
		}
		if (rdn6.isSelected()) {
			noBangku = noBangku + pilm.get(a).tanggal.get(c).waktu.get(b).bangku.get(5).codeB + ",";
		}
		if (rdn7.isSelected()) {
			noBangku = noBangku + pilm.get(a).tanggal.get(c).waktu.get(b).bangku.get(6).codeB + ",";
		}
		if (rdn8.isSelected()) {
			noBangku = noBangku + pilm.get(a).tanggal.get(c).waktu.get(b).bangku.get(7).codeB + ",";
		}
		if (rdn9.isSelected()) {
			noBangku = noBangku + pilm.get(a).tanggal.get(c).waktu.get(b).bangku.get(8).codeB + ",";
		}
		if (rdn10.isSelected()) {
			noBangku = noBangku + pilm.get(a).tanggal.get(c).waktu.get(b).bangku.get(9).codeB + ",";
		}
		if (rdn11.isSelected()) {
			noBangku = noBangku + pilm.get(a).tanggal.get(c).waktu.get(b).bangku.get(10).codeB + ",";
		}
		if (rdn12.isSelected()) {
			noBangku = noBangku + pilm.get(a).tanggal.get(c).waktu.get(b).bangku.get(11).codeB + ",";
		}
		if (rdn13.isSelected()) {
			noBangku = noBangku + pilm.get(a).tanggal.get(c).waktu.get(b).bangku.get(12).codeB + ",";
		}
		if (rdn14.isSelected()) {
			noBangku = noBangku + pilm.get(a).tanggal.get(c).waktu.get(b).bangku.get(13).codeB + ",";
		}
		if (rdn15.isSelected()) {
			noBangku = noBangku + pilm.get(a).tanggal.get(c).waktu.get(b).bangku.get(14).codeB + ",";
		}
		if (rdn16.isSelected()) {
			noBangku = noBangku + pilm.get(a).tanggal.get(c).waktu.get(b).bangku.get(15).codeB + ",";
		}
		if (rdn17.isSelected()) {
			noBangku = noBangku + pilm.get(a).tanggal.get(c).waktu.get(b).bangku.get(16).codeB + ",";
		}
		if (rdn18.isSelected()) {
			noBangku = noBangku + pilm.get(a).tanggal.get(c).waktu.get(b).bangku.get(17).codeB + ",";
		}
		return noBangku;
	}

	public void refresh() {
		reset();
		int a = pilFilm.getSelectedIndex();
		int b = pilWaktu.getSelectedIndex();
		int c = pilTanggal.getSelectedIndex();
		if (pilm.get(a).tanggal.get(c).waktu.get(b).bangku.get(0).isDigunakan() == false) {
			rdn1.setEnabled(true);
		}
		if (pilm.get(a).tanggal.get(c).waktu.get(b).bangku.get(1).isDigunakan() == false) {
			rdn2.setEnabled(true);
		}
		if (pilm.get(a).tanggal.get(c).waktu.get(b).bangku.get(2).isDigunakan() == false) {
			rdn3.setEnabled(true);
		}

		if (pilm.get(a).tanggal.get(c).waktu.get(b).bangku.get(3).isDigunakan() == false) {
			rdn4.setEnabled(true);
		}
		if (pilm.get(a).tanggal.get(c).waktu.get(b).bangku.get(4).isDigunakan() == false) {
			rdn5.setEnabled(true);
		}
		if (pilm.get(a).tanggal.get(c).waktu.get(b).bangku.get(5).isDigunakan() == false) {
			rdn6.setEnabled(true);
		}

		if (pilm.get(a).tanggal.get(c).waktu.get(b).bangku.get(6).isDigunakan() == false) {
			rdn7.setEnabled(true);
		}
		if (pilm.get(a).tanggal.get(c).waktu.get(b).bangku.get(7).isDigunakan() == false) {
			rdn8.setEnabled(true);
		}
		if (pilm.get(a).tanggal.get(c).waktu.get(b).bangku.get(8).isDigunakan() == false) {
			rdn9.setEnabled(true);
		}

		if (pilm.get(a).tanggal.get(c).waktu.get(b).bangku.get(9).isDigunakan() == false) {
			rdn10.setEnabled(true);
		}
		if (pilm.get(a).tanggal.get(c).waktu.get(b).bangku.get(10).isDigunakan() == false) {
			rdn11.setEnabled(true);
		}
		if (pilm.get(a).tanggal.get(c).waktu.get(b).bangku.get(11).isDigunakan() == false) {
			rdn12.setEnabled(true);
		}

		if (pilm.get(a).tanggal.get(c).waktu.get(b).bangku.get(12).isDigunakan() == false) {
			rdn13.setEnabled(true);
		}
		if (pilm.get(a).tanggal.get(c).waktu.get(b).bangku.get(13).isDigunakan() == false) {
			rdn14.setEnabled(true);
		}
		if (pilm.get(a).tanggal.get(c).waktu.get(b).bangku.get(14).isDigunakan() == false) {
			rdn15.setEnabled(true);
		}

		if (pilm.get(a).tanggal.get(c).waktu.get(b).bangku.get(15).isDigunakan() == false) {
			rdn16.setEnabled(true);
		}
		if (pilm.get(a).tanggal.get(c).waktu.get(b).bangku.get(16).isDigunakan() == false) {
			rdn17.setEnabled(true);
		}
		if (pilm.get(a).tanggal.get(c).waktu.get(b).bangku.get(17).isDigunakan() == false) {
			rdn18.setEnabled(true);
		}
	}

	public void reset() {
		// selected
		rdn1.setSelected(false);
		rdn2.setSelected(false);
		rdn3.setSelected(false);
		rdn4.setSelected(false);
		rdn5.setSelected(false);
		rdn6.setSelected(false);
		rdn7.setSelected(false);
		rdn8.setSelected(false);
		rdn9.setSelected(false);
		rdn10.setSelected(false);
		rdn11.setSelected(false);
		rdn12.setSelected(false);
		rdn13.setSelected(false);
		rdn14.setSelected(false);
		rdn15.setSelected(false);
		rdn16.setSelected(false);
		rdn17.setSelected(false);
		rdn18.setSelected(false);

		// enabeled
		rdn1.setEnabled(false);
		rdn2.setEnabled(false);
		rdn3.setEnabled(false);
		rdn4.setEnabled(false);
		rdn5.setEnabled(false);
		rdn6.setEnabled(false);
		rdn7.setEnabled(false);
		rdn8.setEnabled(false);
		rdn9.setEnabled(false);
		rdn10.setEnabled(false);
		rdn11.setEnabled(false);
		rdn12.setEnabled(false);
		rdn13.setEnabled(false);
		rdn14.setEnabled(false);
		rdn15.setEnabled(false);
		rdn16.setEnabled(false);
		rdn17.setEnabled(false);
		rdn18.setEnabled(false);
	}

	private JTextArea taSaldoo;
	private JTextArea taSaldoTp;
	private JTextArea taNamaTp;
	private DefaultTableModel TabelModel;
	private JFrame MenuTopUp;
	private JTextField tfTopUp;
	public JFrame MenuLogin;
	private JTextField InptNama;
	public JFrame menuTiket;
	private JFrame MainMenu;
	private JTextArea taMenu1;
	private JTable table;
	private JLabel lblJumlahtiket;
	private JButton tiketBeli;
	private JButton btnPilihBangku;
	private JComboBox<String> pilWaktu;
	private JComboBox<String> pilTanggal;
	private JComboBox<String> pilFilm;

	private JFrame MenuPilihBangku;
	private JRadioButton rdn1;
	private JRadioButton rdn2;
	private JRadioButton rdn3;
	private JRadioButton rdn4;
	private JRadioButton rdn5;
	private JRadioButton rdn6;
	private JRadioButton rdn7;
	private JRadioButton rdn8;
	private JRadioButton rdn9;
	private JRadioButton rdn10;
	private JRadioButton rdn11;
	private JRadioButton rdn12;
	private JRadioButton rdn13;
	private JRadioButton rdn14;
	private JRadioButton rdn15;
	private JRadioButton rdn16;
	private JRadioButton rdn17;
	private JRadioButton rdn18;
}