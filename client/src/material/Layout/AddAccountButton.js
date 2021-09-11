import React, {useRef} from 'react';
import {makeStyles} from '@material-ui/core/styles';
import Modal from '@material-ui/core/Modal';
import Button from "@material-ui/core/Button";
import axios from "axios";

function rand() {
    return Math.round(Math.random() * 20) - 10;
}

function getModalStyle() {
    const top = 50 + rand();
    const left = 50 + rand();

    return {
        top: `${top}%`,
        left: `${left}%`,
        transform: `translate(-${top}%, -${left}%)`,
    };
}

const useStyles = makeStyles((theme) => ({
    paper: {
        position: 'absolute',
        width: 400,
        backgroundColor: theme.palette.background.paper,
        border: '2px solid #000',
        boxShadow: theme.shadows[5],
        padding: theme.spacing(2, 4, 3),
        display: "flex",
        justifyContent: "center",
        alignItems: "center",
        flexDirection: "column"
    },
    openModal: {
        backgroundColor: "green",
        color: "white"
    }
}));

export default function SimpleModal({color, caption, data}) {
    const classes = useStyles();
    // getModalStyle is not a pure function, we roll the style only on the first render
    const [modalStyle] = React.useState(getModalStyle);
    const [open, setOpen] = React.useState(false);

    const number = useRef(null);
    const currency = useRef(null);
    const balance = useRef(null);
    const customerID = useRef(null);

    const handleOpen = () => {
        setOpen(true);
    };

    const handleClose = () => {
        setOpen(false);
    };

    const addAccount = () => {
        if (number.current.value !== "" && currency.current.value !== "" && balance.current.value !== "" && customerID.current.value !== "") {
            axios.post(`customer/${customerID.current.value}/account`, {
                number: number.current.value,
                currency: currency.current.value,
                balance: balance.current.value
            });

            window.location.reload();
        }
    };

    const body = (
        <div style={modalStyle} className={classes.paper}>
            <input ref={number} type="text" placeholder="Enter number"/>

            <select ref={currency} id="cars">
                <option value="0">USD</option>
                <option value="1">EUR</option>
                <option value="2">UAH</option>
                <option value="3">CHF</option>
                <option value="4">GBP</option>
            </select>

            <input ref={balance} type="number" min="0" placeholder="Enter balance"/>
            <input ref={customerID} type="number" min="0" placeholder="Enter customer ID"/>
            <hr/>
            <Button color="primary" onClick={addAccount}>Add Account</Button>
        </div>
    );

    return (
        <div>
            <Button className={classes.openModal} type="button" onClick={handleOpen}>
                Add Account
            </Button>
            <Modal
                open={open}
                onClose={handleClose}
                aria-labelledby="simple-modal-title"
                aria-describedby="simple-modal-description"
            >
                {body}
            </Modal>
        </div>
    );
}