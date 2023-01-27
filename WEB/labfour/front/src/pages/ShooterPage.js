import FormAndGraph from "../components/FormAndGraph";
import Table from "../components/table/Table";
import React from "react";
import {Navigate} from "react-router-dom";
import {isLogin} from "../utils/auth";

export default function ShooterPage(props) {
    if (isLogin()) {
        return (
            <div>
                <FormAndGraph hitInfos={props.hitInfos} addAction={props.addHit}/>
                <Table data={props.hitInfos}/>
            </div>
        );
    } else {
        return (<Navigate to="/login"/>);
    }

}