CREATE TABLE IF NOT EXISTS  prontuario_paciente(
    Id INT PRIMARY KEY AUTO_INCREMENT,
    registro_agenda INT NOT NULL,
    historico TEXT NOT NULL,
    receituario TEXT NOT NULL,
    exames TEXT NOT NULL
) ENGINE=InnoDB;