import {getCookie, setCookie} from "./cookie";

export function getUsername() {
    let username = getCookie("user");
    return username && username !== "guest" ? username : ""
}

export function isLogin() {
    return !!getUsername()
}

export function setUsername(username) {
    setCookie("user", username)
}

export function removeUsername() {
    setCookie("user", "guest")
}