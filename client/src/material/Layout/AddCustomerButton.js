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
        backgroundColor: "yellow",
        color: "white"
    }
}));

export default function SimpleModal({color, caption, data}) {
    const classes = useStyles();
    // getModalStyle is not a pure function, we roll the style only on the first render
    const [modalStyle] = React.useState(getModalStyle);
    const [open, setOpen] = React.useState(false);

    const name = useRef(null);
    const mail = useRef(null);
    const age = useRef(null);

    const handleOpen = () => {
        setOpen(true);
    };

    const handleClose = () => {
        setOpen(false);
    };

    const addCustomer = () => {
        if (name.current.value !== "" && name.current.value !== "" && age.current.value !== "") {
            axios.post('customer/create', {
                name: name.current.value,
                email: name.current.value,
                age: age.current.value,
            });

            window.location.reload();
        }
    };

    const body = (
        <div style={modalStyle} className={classes.paper}>
            <input ref={name} type="text" placeholder="Enter name"/>
            <input ref={mail} type="email" placeholder="Enter e-mail"/>
            <input ref={age} type="number" min="18" placeholder="Enter age"/>
            <hr/>
            <Button color="primary" onClick={addCustomer} >Add Customer</Button>
        </div>
    );

    return (
        <div>
            <Button className={classes.openModal} type="button" onClick={handleOpen}>
                Add Customer
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