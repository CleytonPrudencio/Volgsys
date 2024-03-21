package com.volgsys.api.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<UsuarioAcesso, Long> {
    public Optional<UsuarioAcesso> findByCpfcnpj(String cpfcnpj);

}
