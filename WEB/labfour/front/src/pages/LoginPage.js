import React from "react";
import {isLogin, setUsername} from "../utils/auth";
import {Navigate} from "react-router-dom";
import {checkPassword, checkUsername} from "../utils/authForm";

class LoginPage extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            usernameIsValid: undefined,
            passwordIsValid: undefined,
            disableBtn: false
        }
        this.tryLogin = this.tryLogin.bind(this);
        this.tryReg = this.tryReg.bind(this);
    }

    getClassName(isValid) {
        let className = "text-field-wrapper"
        if (isValid === undefined) {
            return className;
        } else if (isValid) {
            return className + " correct"
        } else {
            return className + " wrong"
        }
    }

    tryLogin(evt) {
        let username = document.getElementById("username").value,
            password = document.getElementById("password").value
        if (checkPassword(password) && checkUsername(username)) {
            this.setState({disableBtn: true})
            fetch(`/labfour/api/trylogin/${username}/${password}`)
                .then((response) => {
                    if (!response.ok) {
                        throw new Error(response.statusText)
                    }
                    return response;
                })
                .then((response) => response.json())
                .then((loginStatus) => {
                    if (loginStatus.authIsSuccess) {
                        setUsername(username)
                    }
                    this.setState({
                        usernameIsValid: !loginStatus.wrongLogin,
                        passwordIsValid: !loginStatus.wrongPassword,
                        disableBtn: false
                    })
                })
        } else {
            this.setState({
                usernameIsValid: checkUsername(username),
                passwordIsValid: checkPassword(password)
            })
        }
    }

    tryReg(evt) {
        let username = document.getElementById("username").value,
            password = document.getElementById("password").value
        if (!checkUsername(username)) this.setState({usernameIsValid: false})
        if (!checkPassword(password)) this.setState({passwordIsValid: false})
        if (checkPassword(password) && checkUsername(username)) {
            this.setState({disableBtn: true})
            fetch(`/labfour/api/adduser/${username}/${password}`)
                .then((response) => {
                    if (!response.ok) {
                        throw new Error(response.statusText)
                    }
                    return response;
                })
                .then((response) => response.json())
                .then((regStatus) => {
                    if (regStatus.status) {
                        setUsername(username)
                    } else {
                        this.setState({usernameIsValid: false})
                    }
                    this.setState({disableBtn: false})
                })
        }
    }

    render() {
        if (isLogin()) {
            return (<Navigate to="/"/>);
        } else {
            return (
                <div className="form-wrapper mt-75">
                    <form className="container">
                        <h2>Регистрация / Вход</h2>
                        <div className={this.getClassName(this.state.usernameIsValid)}>
                            <label htmlFor="username" className="form-label">Логин</label>
                            <input name="username" id="username" type="text"/>
                        </div>
                        <div className={this.getClassName(this.state.passwordIsValid)}>
                            <label htmlFor="password" className="form-label">Пароль</label>
                            <input name="password" id="password" type="password"/>
                        </div>
                        <div className="btns-block">
                            <button id="submitBtn" type="button" className="btn btn-block secondary"
                            disabled={this.state.disableBtn} onClick={this.tryReg}>Регистрация</button>
                            <button id="submitBtn" type="button" className="btn btn-block primary"
                            disabled={this.state.disableBtn} onClick={this.tryLogin}>Войти</button>
                        </div>
                    </form>
                </div>
            )
        }
    }
}


export default LoginPage;