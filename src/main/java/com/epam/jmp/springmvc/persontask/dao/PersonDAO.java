package com.epam.jmp.springmvc.persontask.dao;

import com.epam.jmp.springmvc.persontask.entity.Person;
import com.epam.jmp.springmvc.persontask.exceptions.ConnectionPoolException;
import com.epam.jmp.springmvc.persontask.exceptions.DAOException;
import com.epam.jmp.springmvc.persontask.pool.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonDAO {
	private static final String FIND_ALL = "select id, first_name, last_name, age, address, salary from person order by id asc";
	private static final String INSERT_PERSON = "insert into person (first_name, last_name, age, address, salary) values (?, ?, ?, ?, ?)";
	private static final String FETCH_PERSON_BY_ID = "select id, first_name, last_name, age, address, salary from person where id = ? order by id asc";
	private static final String UPDATE_PERSON = "update person set first_name = ?, last_name = ?, age = ?, address = ?, salary = ?  WHERE id = ?";
	private static final String DELETE_PERSON = "delete from person where id = ?";

	private static final String PARAMETER_NAME_ID = "id";
	private static final String PARAMETER_NAME_FIRST_NAME = "first_name";
	private static final String PARAMETER_NAME_LAST_NAME = "last_name";
	private static final String PARAMETER_NAME_AGE = "age";
	private static final String PARAMETER_NAME_ADDRESS = "address";
	private static final String PARAMETER_NAME_SALARY = "salary";

	private ConnectionPool connectionPool;

	public List readPersonsList() throws DAOException, ConnectionPoolException {
		List<Person> result = new ArrayList<Person>();
		Connection connection = connectionPool.getResource();
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(FIND_ALL);
			while (resultSet.next()) {
				Person person = new Person();
				person.setId(resultSet.getInt(PARAMETER_NAME_ID));
				person.setFirstName(resultSet.getString(PARAMETER_NAME_FIRST_NAME));
				person.setLastName(resultSet.getString(PARAMETER_NAME_LAST_NAME));
				person.setAge(resultSet.getInt(PARAMETER_NAME_AGE));
				person.setAddress(resultSet.getString(PARAMETER_NAME_ADDRESS));
				person.setSalary(resultSet.getInt(PARAMETER_NAME_SALARY));
				result.add(person);
			}
		} catch (SQLException ex) {
			throw new ConnectionPoolException(ex.getMessage());
		} finally {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException ex) {
					throw new DAOException(ex.getMessage());
				}
			}
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException ex) {
					throw new ConnectionPoolException(ex.getMessage());
				}
			}
			if (connection != null) {
				ConnectionPool.returnResource(connection);
			}
		}
		return result;
	}

	public void savePerson(Person person) throws ConnectionPoolException, DAOException {
		Connection connection = connectionPool.getResource();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			preparedStatement = connection.prepareStatement(INSERT_PERSON);
			preparedStatement.setString(1, person.getFirstName());
			preparedStatement.setString(2, person.getLastName());
			preparedStatement.setInt(3, person.getAge());
			preparedStatement.setString(4, person.getAddress());
			preparedStatement.setInt(5, person.getSalary());
			preparedStatement.executeUpdate();
		} catch (SQLException ex) {
			throw new DAOException(ex.getMessage());
		} finally {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException ex) {
					throw new DAOException(ex.getMessage());
				}
			}
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException ex) {
					throw new ConnectionPoolException(ex.getMessage());
				}
			}
			if (connection != null) {
				ConnectionPool.returnResource(connection);
			}
		}
	}

	public Person getPersonById(int id) throws ConnectionPoolException, DAOException {
		Connection connection = connectionPool.getResource();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Person person = null;
		try {
			preparedStatement = connection.prepareStatement(FETCH_PERSON_BY_ID);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				person = new Person();
				person.setId(resultSet.getInt("id"));
				person.setFirstName(resultSet.getString("first_name"));
				person.setLastName(resultSet.getString("last_name"));
				person.setAge(resultSet.getInt("age"));
				person.setAddress(resultSet.getString("address"));
				person.setSalary(resultSet.getInt("salary"));
			}
		} catch (SQLException ex) {
			throw new DAOException(ex.getMessage());
		} finally {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException ex) {
					throw new DAOException(ex.getMessage());
				}
			}
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException ex) {
					throw new DAOException(ex.getMessage());
				}
			}
			if (connection != null) {
				ConnectionPool.returnResource(connection);
			}
		}
		return person;
	}

	public void updatePerson(Person person) throws ConnectionPoolException, DAOException {
		Connection connection = connectionPool.getResource();
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(UPDATE_PERSON);
			ps.setString(1, person.getFirstName());
			ps.setString(2, person.getLastName());
			ps.setInt(3, person.getAge());
			ps.setString(4, person.getAddress());
			ps.setInt(5, person.getSalary());
			ps.setInt(6, person.getId());
			ps.executeUpdate();
		} catch (SQLException ex) {
			throw new DAOException(ex.getMessage());
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException ex) {
					throw new DAOException(ex.getMessage());
				}
			}
			if (connection != null) {
				ConnectionPool.returnResource(connection);
			}
		}
	}

	public void deletePerson(int id) throws ConnectionPoolException, DAOException {
		Connection connection = connectionPool.getResource();
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(DELETE_PERSON);
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
		} catch (SQLException ex) {
			throw new DAOException(ex.getMessage());
		} finally {
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException ex) {
					throw new DAOException(ex.getMessage());
				}
			}
			if (connection != null) {
				ConnectionPool.returnResource(connection);
			}
		}
	}

	public ConnectionPool getConnectionPool() {
		return connectionPool;
	}

	public void setConnectionPool(ConnectionPool connectionPool) {
		this.connectionPool = connectionPool;
	}
}
