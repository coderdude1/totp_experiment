package com.dood.app.entities;

import com.google.common.base.MoreObjects;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * Created by Randy on 1/24/14.
 * TODO add jackson annotations
 *
 */
@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Basic
	@Column(name = "first_name")
	private String firstName;

	@Basic
	@Column(name = "last_name")
	private String lastName;

//    @Email
	@Basic
    @Column(name = "email")
	private String email;

    @Basic
    @Column(name = "enabled")
    private boolean enabled;

    @Basic
    @Column(name = "password_hash")
    private String password;

    @Basic
    @Column(name = "totp_secret")
    private String totpSecret;

    @Basic
    @Column(name = "totp_secret_create_date")
    private Date totpSecretDate;

    @Basic
    @Column(name = "two_factor_auth_enabled")
    private boolean twoFactorAuthEnabled;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    //todo add int failedLogis, Date lockedOut, Date expireDate

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String name) {
		this.firstName = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTotpSecret() {
        return totpSecret;
    }

    public void setTotpSecret(String totpSecret) {
        this.totpSecret = totpSecret;
    }

    public Date getTotpSecretDate() {
        return totpSecretDate;
    }

    public void setTotpSecretDate(Date totpSecretDate) {
        this.totpSecretDate = totpSecretDate;
    }

    public boolean isTwoFactorAuthEnabled() {
        return twoFactorAuthEnabled;
    }

    public void setTwoFactorAuthEnabled(boolean twoFactorAuthEnabled) {
        this.twoFactorAuthEnabled = twoFactorAuthEnabled;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("firstName", firstName)
                .add("lastName", lastName)
                .add("email", email)
                .add("enabled", enabled)
                .add("password", password)
                .add("totpSecret", totpSecret)
                .add("totpSecretDate", totpSecretDate)
                .add("twoFactorAuthEnabled", twoFactorAuthEnabled)
                .add("roles", roles)
                .toString();
    }
}
