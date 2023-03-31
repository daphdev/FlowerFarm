import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.regex.Pattern;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import model.Register;

// In RegisterDAO.java
	@Override
	public List<Register> read() {
		List<Register> list = new ArrayList<>();
		try {
			CsvParser.mapTo(Register.class).forEach(new File(REGISTER_CSV_PATH), System.out::println);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}
	

// In App.java
		long startTime = System.nanoTime();
		
		long endTime = System.nanoTime();
		long timeElapsed = endTime - startTime;

		System.out.println("Execution time in nanoseconds  : " + timeElapsed);

		System.out.println("Execution time in milliseconds : " + timeElapsed / 1000000);
		
// In RegisterDAO.java (can't use)
		@Override
		@SuppressWarnings({ "rawtypes", "unchecked" })
		public List<Register> read() {
			List<Register> list = new ArrayList<>();
			try {
				String[] columns = { "id", "username", "password" };
				ColumnPositionMappingStrategy strategy = new ColumnPositionMappingStrategy();
				strategy.setType(Register.class);
				strategy.setColumnMapping(columns);
				
				Reader reader = Files.newBufferedReader(Paths.get(REGISTER_CSV_PATH));
				CsvToBean csvToBean = new CsvToBeanBuilder(reader).withMappingStrategy(strategy).withSkipLines(1)
						.withIgnoreLeadingWhiteSpace(true).build();
				list = csvToBean.parse();
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return list;
		}
		
// Old read() in RegisterDAO.java
		@Override
		public List<Register> read() {
			List<Register> list = new ArrayList<>();
			try (BufferedReader reader = Files.newBufferedReader(Paths.get(REGISTER_CSV_PATH));
					CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader()
							.withSkipHeaderRecord().withIgnoreHeaderCase().withTrim());) {
				for (CSVRecord csvRecord : csvParser.getRecords()) {
					list.add(new Register(csvRecord.get("id"), csvRecord.get("username"), csvRecord.get("password")));
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			return list;
		}

// read() with BeanUtils
		@Override
		public List<Register> read() {
			List<Register> list = new ArrayList<>();
			try (BufferedReader reader = Files.newBufferedReader(Paths.get(REGISTER_CSV_PATH));
					CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader()
							.withSkipHeaderRecord().withIgnoreHeaderCase().withTrim());) {
				for (CSVRecord csvRecord : csvParser.getRecords()) {
					Register register = new Register();
					for (Entry<String, String> entry : csvRecord.toMap().entrySet()) {
						BeanUtils.setProperty(register, entry.getKey(), entry.getValue());
					}
					list.add(register);
				}
			} catch (IOException | IllegalAccessException | InvocationTargetException e) {
				e.printStackTrace();
			}
			return list;
		}

// Old write()
		@Override
		public void write(List<Register> list) {
			try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(REGISTER_CSV_PATH));
					CSVPrinter csvPrinter = new CSVPrinter(writer,
							CSVFormat.DEFAULT.withHeader("id", "username", "password"));) {
				for (Register register : list) {
					csvPrinter.printRecord(register.getId(), register.getUsername(), register.getPassword());
				}
				csvPrinter.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
// Old code 23/6/2020: WelcomeView.java
		@SuppressWarnings("deprecation")
		public Register getRegister() {
			if (!validateUsernameField() || !validatePasswordField()) {
				return null;
			}
			try {
				return new Register(usernameField.getText().trim(), passwordField.getText().trim());
			} catch (Exception e) {
				showMessage(e.getMessage());
			}
			return null;
		}

		private boolean validateUsernameField() {
			String text = usernameField.getText();
			if (text == null || "".equals(text.trim())) {
				usernameField.requestFocus();
				showMessage("Username can't be blank. Please input again.");
				return false;
			}
			if (!Pattern.matches("([a-zA-Z0-9]+)", text)) {
				usernameField.requestFocus();
				showMessage("Username only contains uppercase letters, lowercase letters and numbers.");
				return false;
			}
			if (registerDAO.getRegisterList().stream().anyMatch(r -> r.getUsername().equals(text))) {
				usernameField.requestFocus();
				showMessage("This username already exists. Please input a new one.");
				return false;
			}
			return true;
		}

		@SuppressWarnings("deprecation")
		private boolean validatePasswordField() {
			String text = passwordField.getText();
			if (text == null || "".equals(text.trim())) {
				passwordField.requestFocus();
				showMessage("Password can't be blank. Please input again.");
				return false;
			}
			if (!Pattern.matches("([a-zA-Z0-9]+)", text)) {
				passwordField.requestFocus();
				showMessage("Password only contains uppercase letters, lowercase letters and numbers.");
				return false;
			}
			return true;
		}