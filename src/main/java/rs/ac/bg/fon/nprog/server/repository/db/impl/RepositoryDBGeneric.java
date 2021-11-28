package rs.ac.bg.fon.nprog.server.repository.db.impl;

import rs.ac.bg.fon.nprog.common.domain.GenericEntity;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import rs.ac.bg.fon.nprog.server.repository.db.DbConnectionFactory;
import rs.ac.bg.fon.nprog.server.repository.db.DbRepository;

public class RepositoryDBGeneric implements DbRepository<GenericEntity> {

	@Override
	public List<GenericEntity> getAll(GenericEntity param) throws Exception {
		List<GenericEntity> list = new ArrayList<>();
		try {
			Connection con = DbConnectionFactory.getInstance().getConnection();
			String query = "select * from " + param.getTableName() + param.getJoinCondition()
					+ param.getConditionForMore() + param.getSort();
			System.out.println(query);
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(query);
			list = (List<GenericEntity>) param.fillListFromRS(rs);
			rs.close();
			s.close();
			return list;
		} catch (SQLException ex) {
			throw ex;
		}
	}

	@Override
	public void add(GenericEntity param) throws Exception {
		try {
			Connection con = DbConnectionFactory.getInstance().getConnection();
			String query = "insert into " + param.getTableName() + "(" + param.getColumnNamesForInsert() + ") values ("
					+ param.getInsertValues() + ")";
			System.out.println(query);
			PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			ps.executeUpdate();

			ResultSet rs = ps.getGeneratedKeys();
			long id = 0;
			while (rs.next()) {
				id = rs.getLong(1);
				param.setId(id);
			}
		} catch (SQLException ex) {
			throw new Exception("Neuspesno cuvanje!\n" + ex.getMessage());
		}
	}

	@Override
	public void edit(GenericEntity param) throws Exception {
		try {
			Connection con = DbConnectionFactory.getInstance().getConnection();
			String query = "update " + param.getTableName() + " set " + param.setUpdateValues() + " where "
					+ param.getConditionForOne();
			System.out.println(query);
			PreparedStatement ps = con.prepareStatement(query);
			ps.executeUpdate();
		} catch (SQLException ex) {
			throw new Exception("Neuspesna izmena!\n" + ex.getMessage());
		}
	}

	@Override
	public void delete(GenericEntity param) throws Exception {
		try {
			Connection con = DbConnectionFactory.getInstance().getConnection();
			String query = "delete from " + param.getTableName() + " where " + param.getConditionForOne();
			System.out.println(query);
			PreparedStatement ps = con.prepareStatement(query);
			ps.executeUpdate();
		} catch (SQLException ex) {
			throw new Exception("Neuspesno brisanje!\n" + ex.getMessage());
		}
	}

	@Override
	public GenericEntity get(GenericEntity param) throws Exception {
		try {
			Connection con = DbConnectionFactory.getInstance().getConnection();
			String query = "select * from " + param.getTableName() + " where " + param.getConditionForOne();
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(query);
			GenericEntity o = param.fillFromRS(rs);
			rs.close();
			s.close();
			return o;
		} catch (SQLException ex) {
			throw ex;
		}
	}

	@Override
	public GenericEntity getWithJoin(GenericEntity param) throws Exception {
		try {
			Connection con = DbConnectionFactory.getInstance().getConnection();
			String query = "select * from " + param.getTableName() + param.getJoinCondition() + " where "
					+ param.getConditionForOne();
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(query);
			GenericEntity o = param.fillFromRS(rs);
			rs.close();
			s.close();
			return o;
		} catch (SQLException ex) {
			throw ex;
		}
	}
}
