export function hitInfos(state = [], action) {
    switch (action.type) {
        case "hitInfos/load":
            return action.hitInfos
        default:
            return state
    }
}