package com.ravtec.gestaoacademica

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns

class UsuariosDB(context: Context) : SQLiteOpenHelper(context, "bd_usuarios", null, 3) {


    object UsuariosEntry : BaseColumns {
        const val TABLE_NAME = "usuarios"
        const val COLUMN_NAME_NOME = "nome"
        const val COLUMN_NAME_NOMEUSUARIO = "nomeusuario"
        const val COLUMN_NAME_CPF = "cpf"
        const val COLUMN_NAME_EMAIL = "email"
        const val COLUMN_NAME_SENHA = "senha"
        const val COLUMN_NAME_TELEFONE = "telefone"
        const val COLUMN_NAME_MATRICULA = "matricula"
    }

    private val SQL_CREATE_ENTRIES =
        "CREATE TABLE ${UsuariosEntry.TABLE_NAME} (" +
                "${UsuariosEntry.COLUMN_NAME_NOME} TEXT," +
                "${UsuariosEntry.COLUMN_NAME_NOMEUSUARIO} TEXT," +
                "${UsuariosEntry.COLUMN_NAME_CPF} TEXT PRIMARY KEY," +
                "${UsuariosEntry.COLUMN_NAME_EMAIL} TEXT," +
                "${UsuariosEntry.COLUMN_NAME_SENHA} TEXT," +
                "${UsuariosEntry.COLUMN_NAME_TELEFONE} INTEGER," +
                "${UsuariosEntry.COLUMN_NAME_MATRICULA} INTEGER)"

    private val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS usuarios"


    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }

    override fun onDowngrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }

}