package com.yash.vta.daoimpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yash.vta.dao.ModuleDao;
import com.yash.vta.dbconnection.MyConnection;
import com.yash.vta.model.Module;
import com.yash.vta.model.Modules;
import com.yash.vta.model.Training;

public class ModuleDaoImpl implements ModuleDao {
	private Connection con = null;

	public void addModule(Module module) {
		int id = 0;
		try {
			con = MyConnection.getConnection();
			Statement stmt = con
					.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
							ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = stmt.executeQuery("select * from modules");
			if (!rs.next()) {
				id = 1;

			} else {
				rs.last();

				id = rs.getInt("module_id") + 1;
			}

			PreparedStatement pst = con
					.prepareStatement("Insert into Modules (module_id,module_name,training_id,Created_by,created_date) values(?,?,?,?,?)");
			pst.setInt(1, id);
			pst.setString(2, module.getModule_name());
			pst.setInt(3, module.getTraining_id());
			pst.setString(4, module.getCreated_by());
			pst.setDate(5, (Date) module.getCreated_date());
			// pst.setString(5, module.getUpdatedBy());
			// pst.setDate(5, (Date) module.getUpdatedDate());
			int a = pst.executeUpdate();
			if (a == 1) {
				con.close();
			}
		} catch (SQLException ex) {
			ex.printStackTrace();

		}
	}

	public List<Modules> getAllModules() {
		List<Modules> modules = new ArrayList<Modules>();
		con = MyConnection.getConnection();
		Module module = null;
		Training training = null;
		Modules mod = null;
		try {
			PreparedStatement pst = con
					.prepareStatement("SELECT m.module_id,m.created_date,m.created_by,t.technology_name,m.module_name FROM modules m,trainings t WHERE t.training_id = m.training_id ;");
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				module = new Module();
				training = new Training();
				training.setTechnology_name(rs.getString("technology_name"));
				module.setModule_id(rs.getInt("module_id"));
				module.setModule_name(rs.getString("module_name"));
				module.setCreated_date(rs.getDate("created_date"));
				module.setCreated_by(rs.getString("created_by"));
				mod = new Modules(training, module);
				modules.add(mod);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return modules;
	}

	public void deleteModule(Module mod) {
		con = MyConnection.getConnection();
		try {
			PreparedStatement pst = con
					.prepareStatement("Delete from Modules where Module_id = ?");
			pst.setInt(1, mod.getModule_id());
			int count = pst.executeUpdate();
			if (count == 1) {
				con.close();
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public void updateModule(Module module) {
		con = MyConnection.getConnection();
		try {
			PreparedStatement pst = con
					.prepareStatement("Update Modules set modified_by =?,modified_date=?,module_name=?,training_id=? where module_id=?");
			pst.setString(1, module.getUpdated_by());
			pst.setDate(2, (Date) module.getUpdated_date());
			pst.setString(3, module.getModule_name());
			pst.setInt(4, module.getTraining_id());
			pst.setInt(5, module.getModule_id());
			int count = pst.executeUpdate();
			if (count == 1) {
				con.close();
				System.out.println("updated");
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

	}

	public Map<String, Integer> trainingIds() {
		Map<String, Integer> trainingIds = new HashMap<String, Integer>();

		try {
			con = MyConnection.getConnection();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("Select * from Trainings");
			while (rs.next()) {
				trainingIds.put(rs.getString("technology_name"),
						rs.getInt("training_id"));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return trainingIds;
	}

	public boolean checkModule(String name, int training_id) {
		Connection connection = MyConnection.getConnection();
		boolean module = false;
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("select * from modules where module_name =? and training_id=?");
			preparedStatement.setString(1, name);
			preparedStatement.setInt(2, training_id);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				module = true;
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return module;
	}

}
