<?php

namespace db;

/**
 * Класс для формирования строк SQL-запросов,
 * каждый метод класса добавляет часть запроса
*/
class StatementMaker {
    private $statement;
    private $tableName;

    public function __construct(string $tableName) {
        $this->statement = '';
        $this->tableName = $tableName;
    }

    public function __toString() {
        return $this->statement . ';';
    }

    final function joinParamsInStr(array $params, string $emptyValue = ''): string {
        /** Соединяет массив строк с разделителем ', ' для использования в SQL запросах */
        if (count($params)) {
            $result = implode(', ', $params);
        } else {
            $result = $emptyValue;
        }
        return $result;
    }

    public function getStatement(): string {
        return $this->statement . ';';
    }

    final function addFrom() {
        $this->statement .= ' FROM ' . $this->tableName;
    }

    final function addSelect(array $fields) {
        $this->statement .= 'SELECT ' . $this->joinParamsInStr($fields, '*');
        $this->addFrom();
    }

    final function addDelete() {
        $this->statement .= 'DELETE ';
        $this->addFrom();
    }

    final function addInsertInto(array $fields, array $values = [], bool $prepare = false) {
        // todo: empty exception
        $this->statement .= 'INSERT INTO ' . $this->tableName . '(' . $this->joinParamsInStr($fields) .
            ') VALUES (';
        if ($prepare) {
            $this->statement .= '?';
            for ($i = 1; $i < count($fields); $i++) {
                $this->statement .= ', ?';
            }
        } else {
            $this->statement .= $this->joinParamsInStr($values);
        }
        $this->statement .= ')';
    }
    
    final function addWhere(string $where) {
        $this->statement .= ' WHERE ' . $where;
    }

    final function addOrderBy(array $fields) {
        $this->statement .= ' ORDER BY ' . $this->joinParamsInStr($fields);
    }

    final function addLeftJoin(string $thisField, string $overTableName, string $overField) {
        $this->statement .= " LEFT JOIN $overTableName ON " . $this->tableName . ".$thisField = $overTableName.$overField";
    }

    public function select(...$fields): StatementMaker {
        $this->addSelect($fields);
        return $this;
    }

    public function where(string $where): StatementMaker {
        $this->addWhere($where);
        return $this;
    }

    public function delete(string $where): StatementMaker {
        $this->addDelete();
        $this->addWhere($where);
        return $this;
    }

    public function insertInto(array $fields, array $values = [], bool $prepare = false): StatementMaker {
        $this->addInsertInto($fields, $values, $prepare);
        return $this;
    }

    public function orderBy(...$fields): StatementMaker {
        $this->addOrderBy($fields);
        return $this;
    }

    public function leftJoin(string $thisField, string $overTableName, string $overField): StatementMaker {
        $this->addLeftJoin($thisField, $overTableName, $overField);
        return $this;
    }
}
