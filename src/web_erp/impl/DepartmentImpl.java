package web_erp.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import web_erp.dao.DepartmentDao;
import web_erp.dto.Department;

public class DepartmentImpl implements DepartmentDao {

	private static final DepartmentImpl instance = new DepartmentImpl();
	
	private Connection con;

	public void setCon(Connection con) {
		this.con = con;
	}

	public static DepartmentImpl getInstance() {
		return instance;
	}

	public DepartmentImpl() {
	}

	@Override
	public List<Department> selectDepartmentByAll() {
		String sql = "select deptNo,deptName,floor from department";
		try (PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {
			if (rs.next()) {
				List<Department> list = new ArrayList<>();
				do {
					list.add(getDepartment(rs));
				} while (rs.next());
				return list;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	private Department getDepartment(ResultSet rs) throws SQLException {
		int deptNo = rs.getInt("deptNo");
		String deptName = rs.getString("deptName");
		int floor = rs.getInt("floor");
		return new Department(deptNo, deptName, floor);
	}	

	@Override
	public Department selectDepartmentByNo(Department department) {
		String sql = "select deptNo, deptName, floor from department where deptNo = ? ";
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, department.getdNo());
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					return getDepartment(rs);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int insertDepartment(Department department) {
		String sql = "insert into department values (?, ?, ?)";
		try(	PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setInt(1, department.getdNo());
			pstmt.setString(2, department.getdName());
			pstmt.setInt(3, department.getFloor());
			return pstmt.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int updateDepartment(Department department) {
		String sql = "update department set deptName = ? where deptNo = ?";
		try(	PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setString(1, department.getdName());
			pstmt.setInt(2, department.getdNo());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int deleteDepartment(int deptNo) {
		String sql = "delete from department where deptno = ?";
		try(	PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setInt(1, deptNo);
			return pstmt.executeUpdate();
			} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

}
