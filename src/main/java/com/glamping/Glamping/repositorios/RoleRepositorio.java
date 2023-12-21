/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.glamping.Glamping.repositorios;

import com.glamping.Glamping.entidades.Role;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Admin
 */
@Repository
public interface RoleRepositorio extends JpaRepository<Role, Integer> {
    Optional<Role> findByAuthority(String authority);
}
