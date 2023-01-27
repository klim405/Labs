export function checkUsername(username) {
    if (username !== "guest") {
        return !!username.match(/^\w+$/)
    }
}

export function checkPassword(username) {
    return !!username.match(/^\w+$/)
}