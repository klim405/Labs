
export function setCookie(name, value) {
    document.cookie = name + "=" + value
}

export function getCookie(name) {
    let cookie = document.cookie.split(";").find(cookie => cookie.trim().startsWith(name))
    if (cookie) {
        return cookie.split("=")[1].trim()
    } else {
        return undefined
    }
}