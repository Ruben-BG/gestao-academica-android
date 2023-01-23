package com.ravtec.gestaoacademica

class UsuarioProfessor(
    override var nome: String, override var cpf: String,
    override var email: String, override var senha: String,
    override var telefone: String, override var endereco: String
) : Usuario(nome, cpf, email, senha, telefone, endereco) {

    var matricula: Int = 0

}