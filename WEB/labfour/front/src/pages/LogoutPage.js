import {Navigate} from "react-router-dom";
import {removeUsername} from "../utils/auth";

export default function LogoutPage(props) {
    props.clearHit()
    removeUsername()
    return (<Navigate to="/login"/>)
}