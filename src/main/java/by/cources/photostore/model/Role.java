package by.cources.photostore.model;


import javax.persistence.*;

import org.hibernate.annotations.Cascade;


@Entity
@Table(name = "role")
public class Role {
	
	@Id
    @GeneratedValue
    @Column(name = "role_id")
    private Long roleId;
	
	@Column(name = "role_name")
	private String roleName;

    
    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

   
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}