package com.excilys.computer_database.dao;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.computer_database.dao.impl.ComputerDAOImpl;
import com.excilys.computer_database.db.ConnectionFactory;
import com.excilys.computer_database.db.DbUtils;
import com.excilys.computer_database.model.Company;
import com.excilys.computer_database.model.Computer;

public class TestComputerDAOImpl {

	// Test queries
	private static final String CREATE_COMPANY_QUERY = "INSERT INTO company (name) values (?)";
	private static final String CREATE_COMPUTER_QUERY = "INSERT INTO computer (name, introduced, discontinued, company_id) values (?, ?, ?, ?)";

	private ComputerDAOImpl computerDAO;
	private Connection connection;
	private Statement statement;
	private Logger logger = LoggerFactory.getLogger(getClass().getName());

	@Before
	public void executeBeforeEachTests() {
		computerDAO = ComputerDAOImpl.getInstance();

		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.createStatement();

			statement.execute("SET FOREIGN_KEY_CHECKS=0");
			statement.executeUpdate("TRUNCATE computer");
			statement.executeUpdate("TRUNCATE company");
			statement.execute("SET FOREIGN_KEY_CHECKS=0");

			PreparedStatement ps = connection.prepareStatement(CREATE_COMPANY_QUERY);
			ps.setString(1, "Dummy Company");
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			logger.error("Cannot truncate table");
		} finally {

		}
	}

	@After
	public void executeAfterEachTests() {
		computerDAO = null;

		try {
			DbUtils.close(statement);
			DbUtils.close(connection);
		} catch (SQLException e) {
		}
	}

	@Test
	public void testGetComputers() throws SQLException {
		List<Computer> computers = computerDAO.getComputers();
		assertEquals(0, computers.size());

		try {
			PreparedStatement ps = connection.prepareStatement(CREATE_COMPUTER_QUERY);
			ps.setString(1, "Dummy computer");
			ps.setTimestamp(2, Timestamp.valueOf("2000-01-01 00:00:00"));
			ps.setTimestamp(3, Timestamp.valueOf("2001-01-01 00:00:00"));
			ps.setInt(4, 1);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			logger.error("Cannot create computer");
		}

		computers = computerDAO.getComputers();
		assertEquals(1, computers.size());
	}
	
	@Test
	public void testGetComputersPage() throws SQLException {
		List<Computer> computers = computerDAO.getComputers();
		assertEquals(0, computers.size());
		
		for(int i = 0; i<50; i++){
			computerDAO.createComputer(new Computer("Dummy computer " + i));
		}

		computers = computerDAO.getComputers();
		assertEquals(50, computers.size());
		assertEquals("Dummy computer 0", computers.get(0).getName());
		assertEquals("Dummy computer 9", computers.get(9).getName());
		
		computers = computerDAO.getComputersPage(15, 0);
		assertEquals(15, computers.size());
		assertEquals("Dummy computer 0", computers.get(0).getName());
		assertEquals("Dummy computer 14", computers.get(14).getName());
		
		computers = computerDAO.getComputersPage(10, 25);
		assertEquals(10, computers.size());
		assertEquals("Dummy computer 25", computers.get(0).getName());
		assertEquals("Dummy computer 34", computers.get(9).getName());
	}

	@Test
	public void testGetComputerById() {
		try {
			PreparedStatement ps = connection.prepareStatement(CREATE_COMPUTER_QUERY);
			ps.setString(1, "Dummy computer");
			ps.setTimestamp(2, Timestamp.valueOf("2000-01-01 00:00:00"));
			ps.setTimestamp(3, Timestamp.valueOf("2001-01-01 00:00:00"));
			ps.setInt(4, 1);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			logger.error("Cannot create computer");
		}

		Computer computer = computerDAO.getComputer(1);
		assertEquals("Dummy computer", computer.getName());
		assertEquals("Dummy Company", computer.getCompany().getName());
	}

	@Test
	public void testCreateComputer() {

		Company company = new Company(1, "Dummy Company");
		Computer computer = new Computer("Dummy computer");
		computer.setIntroduced(LocalDateTime.of(2000, 1, 1, 0, 0));
		computer.setDiscontinued(LocalDateTime.of(2001, 1, 1, 0, 0));
		computer.setCompany(company);

		computerDAO.createComputer(computer);

		assertEquals("Dummy computer", computerDAO.getComputer(1).getName());
	}

	@Test
	public void testUpdateComputer() {
		try {
			PreparedStatement ps = connection.prepareStatement(CREATE_COMPUTER_QUERY);
			ps.setString(1, "Dummy computer");
			ps.setTimestamp(2, Timestamp.valueOf("2000-01-01 00:00:00"));
			ps.setTimestamp(3, Timestamp.valueOf("2001-01-01 00:00:00"));
			ps.setInt(4, 1);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			logger.error("Cannot create computer");
		}

		Company company = new Company(1, "Dummy Company");
		Computer computer = new Computer("Not so dummy computer");
		computer.setId(1);
		computer.setIntroduced(LocalDateTime.of(2000, 1, 1, 0, 0));
		computer.setDiscontinued(LocalDateTime.of(2001, 1, 1, 0, 0));
		computer.setCompany(company);

		computerDAO.updateComputer(computer);

		assertEquals("Not so dummy computer", computerDAO.getComputer(1).getName());
	}

	@Test
	public void testDeleteComputer() {
		try {
			PreparedStatement ps = connection.prepareStatement(CREATE_COMPUTER_QUERY);
			ps.setString(1, "Dummy computer");
			ps.setTimestamp(2, Timestamp.valueOf("2000-01-01 00:00:00"));
			ps.setTimestamp(3, Timestamp.valueOf("2001-01-01 00:00:00"));
			ps.setInt(4, 1);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			logger.error("Cannot create computer");
		}

		computerDAO.deleteComputer(1);
		assertEquals(0, computerDAO.getComputers().size());
	}
}