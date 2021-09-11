import React from 'react';
import {makeStyles} from '@material-ui/core/styles';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Title from './Title';
import ModalButton from './AddAccountButton'
import Typography from "@material-ui/core/Typography";
import Button from "@material-ui/core/Button";
import axios from "axios";

const useStyles = makeStyles((theme) => ({
    seeMore: {
        marginTop: theme.spacing(3),
    }
}));

export default function Orders({name, mail, age, id, accounts}) {
    const classes = useStyles();

    let deleteAccount = (id) => {
        axios.delete("account/delete/" + id);
    }

    let editAccount = (id) => {

    }

    return (
        <React.Fragment>
            <Title>
                <Typography component="h6" variant="subtitle1" color="primary" gutterBottom>
                    {name}&nbsp;&nbsp;&nbsp;
                </Typography>
                <Typography component="h6" variant="subtitle1" color="primary" gutterBottom>
                    {mail}&nbsp;&nbsp;&nbsp;
                </Typography>
                <Typography component="h6" variant="subtitle1" color="primary" gutterBottom>
                    {age}&nbsp;&nbsp;&nbsp;
                </Typography>
            </Title>
            <Table size="small">
                <TableHead>
                    <TableRow>
                        <TableCell>Number</TableCell>
                        <TableCell>Currency</TableCell>
                        <TableCell>Balance</TableCell>
                    </TableRow>
                </TableHead>
                <TableBody>
                    {accounts && accounts.map((i, n) => (
                        mail === i.number ?
                            <TableRow key={n}>
                                <TableCell>{i.number}</TableCell>
                                <TableCell>{i.currency}</TableCell>
                                <TableCell>{i.balance}</TableCell>
                                <TableCell>
                                    <Button variant="contained" color="secondary" onClick={deleteAccount.bind(this, i.id)}>
                                        Delete
                                    </Button>
                                </TableCell>
                            </TableRow> : null
                    ))}
                </TableBody>
            </Table>
            <div className={classes.seeMore}>
                <hr/>
            </div>
        </React.Fragment>
    );
}