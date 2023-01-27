import {hitInfos} from "../redusers/hitInfos";

export function hitInfosFetchDataSuccess(hitInfos) {
    return {
        type: "hitInfos/load",
        hitInfos: hitInfos
    }
}

export function hitInfosFetchData(url) {
    return (dispatch) => {
        fetch(url)
            .then((response) => {
                if (!response.ok) {
                    throw new Error(response.statusText)
                }
                return response;
            })
            .then((response) => response.json())
            .then((hitInfos) => dispatch(hitInfosFetchDataSuccess(hitInfos)))
    }
}

export function hitInfosClear() {
    return (dispatch) => {dispatch(hitInfosFetchDataSuccess([]))}
}

export function hitInfosAddData(addUrl, urlList, user, coordX, coordY, radius) {
    return (dispatch) => {
        fetch(`${addUrl}/${user}/${coordX}/${coordY}/${radius}`)
            .then((response) => {
                if (!response.ok) {
                    throw new Error(response.statusText)
                }
                return response;
            })
            .then((response) => response.json())
            .then((hitInfos) => dispatch(hitInfosFetchData(urlList)))
    }
}