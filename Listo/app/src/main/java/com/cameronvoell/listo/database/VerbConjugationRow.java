package com.cameronvoell.listo.database;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by cameronvoell on 12/10/15.
 */
public class VerbConjugationRow implements Parcelable{

    private String infinitivo;
    private String infinitivoIngles;
    private String gerund;
    private String gerundIngles;
    private String pastParticiple;
    private String pastParticipleIngles;

    //tense 1
    private String indicativePresentIngles;
    private String indicativePresentYo;
    private String indicativePresentTu;
    private String indicativePresentEl;
    private String indicativePresentNos;
    private String indicativePresentVos;
    private String indicativePresentEllos;

    //tense 2
    private String indicativeFuturoIngles;
    private String indicativeFuturoYo;
    private String indicativeFuturoTu;
    private String indicativeFuturoEl;
    private String indicativeFuturoNos;
    private String indicativeFuturoVos;
    private String indicativeFuturoEllos;

    //tense 3
    private String indicativeImperfectoIngles;
    private String indicativeImperfectoYo;
    private String indicativeImperfectoTu;
    private String indicativeImperfectoEl;
    private String indicativeImperfectoNos;
    private String indicativeImperfectoVos;
    private String indicativeImperfectoEllos;

    //tense 4
    private String indicativePreteritoIngles;
    private String indicativePreteritoYo;
    private String indicativePreteritoTu;
    private String indicativePreteritoEl;
    private String indicativePreteritoNos;
    private String indicativePreteritoVos;
    private String indicativePreteritoEllos;

    //tense 5
    private String indicativeConditionalIngles;
    private String indicativeConditionalYo;
    private String indicativeConditionalTu;
    private String indicativeConditionalEl;
    private String indicativeConditionalNos;
    private String indicativeConditionalVos;
    private String indicativeConditionalEllos;

    //tense 6
    private String subjunctivoPresentIngles;
    private String subjunctivoPresentYo;
    private String subjunctivoPresentTu;
    private String subjunctivoPresentEl;
    private String subjunctivoPresentNos;
    private String subjunctivoPresentVos;
    private String subjunctivoPresentEllos;

    //tense 7
    private String subjunctivoImperfectoIngles;
    private String subjunctivoImperfectoYo;
    private String subjunctivoImperfectoTu;
    private String subjunctivoImperfectoEl;
    private String subjunctivoImperfectoNos;
    private String subjunctivoImperfectoVos;
    private String subjunctivoImperfectoEllos;

    //tense 8
    private String subjunctivoFuturoIngles;
    private String subjunctivoFuturoYo;
    private String subjunctivoFuturoTu;
    private String subjunctivoFuturoEl;
    private String subjunctivoFuturoNos;
    private String subjunctivoFuturoVos;
    private String subjunctivoFuturoEllos;

    //tense 9
    private String imperativeIngles;
    private String imperative1;
    private String imperative2;
    private String imperative3;
    private String imperative4;
    private String imperative5;
    private String imperative6;
    private String imperative7;
    private String imperative8;

    public VerbConjugationRow(String infinitivo) {
        this.infinitivo = infinitivo;
    }

    protected VerbConjugationRow(Parcel in) {
        infinitivo = in.readString();
        infinitivoIngles = in.readString();
        gerund = in.readString();
        gerundIngles = in.readString();
        pastParticiple = in.readString();
        pastParticipleIngles = in.readString();
        indicativePresentIngles = in.readString();
        indicativePresentYo = in.readString();
        indicativePresentTu = in.readString();
        indicativePresentEl = in.readString();
        indicativePresentNos = in.readString();
        indicativePresentVos = in.readString();
        indicativePresentEllos = in.readString();
        indicativeFuturoIngles = in.readString();
        indicativeFuturoYo = in.readString();
        indicativeFuturoTu = in.readString();
        indicativeFuturoEl = in.readString();
        indicativeFuturoNos = in.readString();
        indicativeFuturoVos = in.readString();
        indicativeFuturoEllos = in.readString();
        indicativeImperfectoIngles = in.readString();
        indicativeImperfectoYo = in.readString();
        indicativeImperfectoTu = in.readString();
        indicativeImperfectoEl = in.readString();
        indicativeImperfectoNos = in.readString();
        indicativeImperfectoVos = in.readString();
        indicativeImperfectoEllos = in.readString();
        indicativePreteritoIngles = in.readString();
        indicativePreteritoYo = in.readString();
        indicativePreteritoTu = in.readString();
        indicativePreteritoEl = in.readString();
        indicativePreteritoNos = in.readString();
        indicativePreteritoVos = in.readString();
        indicativePreteritoEllos = in.readString();
        indicativeConditionalIngles = in.readString();
        indicativeConditionalYo = in.readString();
        indicativeConditionalTu = in.readString();
        indicativeConditionalEl = in.readString();
        indicativeConditionalNos = in.readString();
        indicativeConditionalVos = in.readString();
        indicativeConditionalEllos = in.readString();
        subjunctivoPresentIngles = in.readString();
        subjunctivoPresentYo = in.readString();
        subjunctivoPresentTu = in.readString();
        subjunctivoPresentEl = in.readString();
        subjunctivoPresentNos = in.readString();
        subjunctivoPresentVos = in.readString();
        subjunctivoPresentEllos = in.readString();
        subjunctivoImperfectoIngles = in.readString();
        subjunctivoImperfectoYo = in.readString();
        subjunctivoImperfectoTu = in.readString();
        subjunctivoImperfectoEl = in.readString();
        subjunctivoImperfectoNos = in.readString();
        subjunctivoImperfectoVos = in.readString();
        subjunctivoImperfectoEllos = in.readString();
        subjunctivoFuturoIngles = in.readString();
        subjunctivoFuturoYo = in.readString();
        subjunctivoFuturoTu = in.readString();
        subjunctivoFuturoEl = in.readString();
        subjunctivoFuturoNos = in.readString();
        subjunctivoFuturoVos = in.readString();
        subjunctivoFuturoEllos = in.readString();
        imperativeIngles = in.readString();
        imperative1 = in.readString();
        imperative2 = in.readString();
        imperative3 = in.readString();
        imperative4 = in.readString();
        imperative5 = in.readString();
        imperative6 = in.readString();
        imperative7 = in.readString();
        imperative8 = in.readString();
    }

    public static final Creator<VerbConjugationRow> CREATOR = new Creator<VerbConjugationRow>() {
        @Override
        public VerbConjugationRow createFromParcel(Parcel in) {
            return new VerbConjugationRow(in);
        }

        @Override
        public VerbConjugationRow[] newArray(int size) {
            return new VerbConjugationRow[size];
        }
    };

    public String getInfinitivo() {
        return infinitivo;
    }

    public void setInfinitivo(String infinitivo) {
        this.infinitivo = infinitivo;
    }

    public String getInfinitivoIngles() {
        return infinitivoIngles;
    }

    public void setInfinitivoIngles(String infinitivoIngles) {
        this.infinitivoIngles = infinitivoIngles;
    }

    public String getGerund() {
        return gerund;
    }

    public void setGerund(String gerund) {
        this.gerund = gerund;
    }

    public String getGerundIngles() {
        return gerundIngles;
    }

    public void setGerundIngles(String gerundIngles) {
        this.gerundIngles = gerundIngles;
    }

    public String getPastParticiple() {
        return pastParticiple;
    }

    public void setPastParticiple(String pastParticiple) {
        this.pastParticiple = pastParticiple;
    }

    public String getPastParticipleIngles() {
        return pastParticipleIngles;
    }

    public void setPastParticipleIngles(String pastParticipleIngles) {
        this.pastParticipleIngles = pastParticipleIngles;
    }

    public String getIndicativePresentIngles() {
        return indicativePresentIngles;
    }

    public void setIndicativePresentIngles(String indicativePresentIngles) {
        this.indicativePresentIngles = indicativePresentIngles;
    }

    public String getIndicativePresentYo() {
        return indicativePresentYo;
    }

    public void setIndicativePresentYo(String indicativePresentYo) {
        this.indicativePresentYo = indicativePresentYo;
    }

    public String getIndicativePresentTu() {
        return indicativePresentTu;
    }

    public void setIndicativePresentTu(String indicativePresentTu) {
        this.indicativePresentTu = indicativePresentTu;
    }

    public String getIndicativePresentEl() {
        return indicativePresentEl;
    }

    public void setIndicativePresentEl(String indicativePresentEl) {
        this.indicativePresentEl = indicativePresentEl;
    }

    public String getIndicativePresentNos() {
        return indicativePresentNos;
    }

    public void setIndicativePresentNos(String indicativePresentNos) {
        this.indicativePresentNos = indicativePresentNos;
    }

    public String getIndicativePresentVos() {
        return indicativePresentVos;
    }

    public void setIndicativePresentVos(String indicativePresentVos) {
        this.indicativePresentVos = indicativePresentVos;
    }

    public String getIndicativePresentEllos() {
        return indicativePresentEllos;
    }

    public void setIndicativePresentEllos(String indicativePresentEllos) {
        this.indicativePresentEllos = indicativePresentEllos;
    }

    public String getIndicativeFuturoIngles() {
        return indicativeFuturoIngles;
    }

    public void setIndicativeFuturoIngles(String indicativeFuturoIngles) {
        this.indicativeFuturoIngles = indicativeFuturoIngles;
    }

    public String getIndicativeFuturoYo() {
        return indicativeFuturoYo;
    }

    public void setIndicativeFuturoYo(String indicativeFuturoYo) {
        this.indicativeFuturoYo = indicativeFuturoYo;
    }

    public String getIndicativeFuturoTu() {
        return indicativeFuturoTu;
    }

    public void setIndicativeFuturoTu(String indicativeFuturoTu) {
        this.indicativeFuturoTu = indicativeFuturoTu;
    }

    public String getIndicativeFuturoEl() {
        return indicativeFuturoEl;
    }

    public void setIndicativeFuturoEl(String indicativeFuturoEl) {
        this.indicativeFuturoEl = indicativeFuturoEl;
    }

    public String getIndicativeFuturoNos() {
        return indicativeFuturoNos;
    }

    public void setIndicativeFuturoNos(String indicativeFuturoNos) {
        this.indicativeFuturoNos = indicativeFuturoNos;
    }

    public String getIndicativeFuturoVos() {
        return indicativeFuturoVos;
    }

    public void setIndicativeFuturoVos(String indicativeFuturoVos) {
        this.indicativeFuturoVos = indicativeFuturoVos;
    }

    public String getIndicativeFuturoEllos() {
        return indicativeFuturoEllos;
    }

    public void setIndicativeFuturoEllos(String indicativeFuturoEllos) {
        this.indicativeFuturoEllos = indicativeFuturoEllos;
    }

    public String getIndicativeImperfectoIngles() {
        return indicativeImperfectoIngles;
    }

    public void setIndicativeImperfectoIngles(String indicativeImperfectoIngles) {
        this.indicativeImperfectoIngles = indicativeImperfectoIngles;
    }

    public String getIndicativeImperfectoYo() {
        return indicativeImperfectoYo;
    }

    public void setIndicativeImperfectoYo(String indicativeImperfectoYo) {
        this.indicativeImperfectoYo = indicativeImperfectoYo;
    }

    public String getIndicativeImperfectoTu() {
        return indicativeImperfectoTu;
    }

    public void setIndicativeImperfectoTu(String indicativeImperfectoTu) {
        this.indicativeImperfectoTu = indicativeImperfectoTu;
    }

    public String getIndicativeImperfectoEl() {
        return indicativeImperfectoEl;
    }

    public void setIndicativeImperfectoEl(String indicativeImperfectoEl) {
        this.indicativeImperfectoEl = indicativeImperfectoEl;
    }

    public String getIndicativeImperfectoNos() {
        return indicativeImperfectoNos;
    }

    public void setIndicativeImperfectoNos(String indicativeImperfectoNos) {
        this.indicativeImperfectoNos = indicativeImperfectoNos;
    }

    public String getIndicativeImperfectoVos() {
        return indicativeImperfectoVos;
    }

    public void setIndicativeImperfectoVos(String indicativeImperfectoVos) {
        this.indicativeImperfectoVos = indicativeImperfectoVos;
    }

    public String getIndicativeImperfectoEllos() {
        return indicativeImperfectoEllos;
    }

    public void setIndicativeImperfectoEllos(String indicativeImperfectoEllos) {
        this.indicativeImperfectoEllos = indicativeImperfectoEllos;
    }

    public String getIndicativePreteritoIngles() {
        return indicativePreteritoIngles;
    }

    public void setIndicativePreteritoIngles(String indicativePreteritoIngles) {
        this.indicativePreteritoIngles = indicativePreteritoIngles;
    }

    public String getIndicativePreteritoYo() {
        return indicativePreteritoYo;
    }

    public void setIndicativePreteritoYo(String indicativePreteritoYo) {
        this.indicativePreteritoYo = indicativePreteritoYo;
    }

    public String getIndicativePreteritoTu() {
        return indicativePreteritoTu;
    }

    public void setIndicativePreteritoTu(String indicativePreteritoTu) {
        this.indicativePreteritoTu = indicativePreteritoTu;
    }

    public String getIndicativePreteritoEl() {
        return indicativePreteritoEl;
    }

    public void setIndicativePreteritoEl(String indicativePreteritoEl) {
        this.indicativePreteritoEl = indicativePreteritoEl;
    }

    public String getIndicativePreteritoNos() {
        return indicativePreteritoNos;
    }

    public void setIndicativePreteritoNos(String indicativePreteritoNos) {
        this.indicativePreteritoNos = indicativePreteritoNos;
    }

    public String getIndicativePreteritoVos() {
        return indicativePreteritoVos;
    }

    public void setIndicativePreteritoVos(String indicativePreteritoVos) {
        this.indicativePreteritoVos = indicativePreteritoVos;
    }

    public String getIndicativePreteritoEllos() {
        return indicativePreteritoEllos;
    }

    public void setIndicativePreteritoEllos(String indicativePreteritoEllos) {
        this.indicativePreteritoEllos = indicativePreteritoEllos;
    }

    public String getIndicativeConditionalIngles() {
        return indicativeConditionalIngles;
    }

    public void setIndicativeConditionalIngles(String indicativeConditionalIngles) {
        this.indicativeConditionalIngles = indicativeConditionalIngles;
    }

    public String getIndicativeConditionalYo() {
        return indicativeConditionalYo;
    }

    public void setIndicativeConditionalYo(String indicativeConditionalYo) {
        this.indicativeConditionalYo = indicativeConditionalYo;
    }

    public String getIndicativeConditionalTu() {
        return indicativeConditionalTu;
    }

    public void setIndicativeConditionalTu(String indicativeConditionalTu) {
        this.indicativeConditionalTu = indicativeConditionalTu;
    }

    public String getIndicativeConditionalEl() {
        return indicativeConditionalEl;
    }

    public void setIndicativeConditionalEl(String indicativeConditionalEl) {
        this.indicativeConditionalEl = indicativeConditionalEl;
    }

    public String getIndicativeConditionalNos() {
        return indicativeConditionalNos;
    }

    public void setIndicativeConditionalNos(String indicativeConditionalNos) {
        this.indicativeConditionalNos = indicativeConditionalNos;
    }

    public String getIndicativeConditionalVos() {
        return indicativeConditionalVos;
    }

    public void setIndicativeConditionalVos(String indicativeConditionalVos) {
        this.indicativeConditionalVos = indicativeConditionalVos;
    }

    public String getIndicativeConditionalEllos() {
        return indicativeConditionalEllos;
    }

    public void setIndicativeConditionalEllos(String indicativeConditionalEllos) {
        this.indicativeConditionalEllos = indicativeConditionalEllos;
    }

    public String getSubjunctivoPresentIngles() {
        return subjunctivoPresentIngles;
    }

    public void setSubjunctivoPresentIngles(String subjunctivoPresentIngles) {
        this.subjunctivoPresentIngles = subjunctivoPresentIngles;
    }

    public String getSubjunctivoPresentYo() {
        return subjunctivoPresentYo;
    }

    public void setSubjunctivoPresentYo(String subjunctivoPresentYo) {
        this.subjunctivoPresentYo = subjunctivoPresentYo;
    }

    public String getSubjunctivoPresentTu() {
        return subjunctivoPresentTu;
    }

    public void setSubjunctivoPresentTu(String subjunctivoPresentTu) {
        this.subjunctivoPresentTu = subjunctivoPresentTu;
    }

    public String getSubjunctivoPresentEl() {
        return subjunctivoPresentEl;
    }

    public void setSubjunctivoPresentEl(String subjunctivoPresentEl) {
        this.subjunctivoPresentEl = subjunctivoPresentEl;
    }

    public String getSubjunctivoPresentNos() {
        return subjunctivoPresentNos;
    }

    public void setSubjunctivoPresentNos(String subjunctivoPresentNos) {
        this.subjunctivoPresentNos = subjunctivoPresentNos;
    }

    public String getSubjunctivoPresentVos() {
        return subjunctivoPresentVos;
    }

    public void setSubjunctivoPresentVos(String subjunctivoPresentVos) {
        this.subjunctivoPresentVos = subjunctivoPresentVos;
    }

    public String getSubjunctivoPresentEllos() {
        return subjunctivoPresentEllos;
    }

    public void setSubjunctivoPresentEllos(String subjunctivoPresentEllos) {
        this.subjunctivoPresentEllos = subjunctivoPresentEllos;
    }

    public String getSubjunctivoImperfectoIngles() {
        return subjunctivoImperfectoIngles;
    }

    public void setSubjunctivoImperfectoIngles(String subjunctivoImperfectoIngles) {
        this.subjunctivoImperfectoIngles = subjunctivoImperfectoIngles;
    }

    public String getSubjunctivoImperfectoYo() {
        return subjunctivoImperfectoYo;
    }

    public void setSubjunctivoImperfectoYo(String subjunctivoImperfectoYo) {
        this.subjunctivoImperfectoYo = subjunctivoImperfectoYo;
    }

    public String getSubjunctivoImperfectoTu() {
        return subjunctivoImperfectoTu;
    }

    public void setSubjunctivoImperfectoTu(String subjunctivoImperfectoTu) {
        this.subjunctivoImperfectoTu = subjunctivoImperfectoTu;
    }

    public String getSubjunctivoImperfectoEl() {
        return subjunctivoImperfectoEl;
    }

    public void setSubjunctivoImperfectoEl(String subjunctivoImperfectoEl) {
        this.subjunctivoImperfectoEl = subjunctivoImperfectoEl;
    }

    public String getSubjunctivoImperfectoNos() {
        return subjunctivoImperfectoNos;
    }

    public void setSubjunctivoImperfectoNos(String subjunctivoImperfectoNos) {
        this.subjunctivoImperfectoNos = subjunctivoImperfectoNos;
    }

    public String getSubjunctivoImperfectoVos() {
        return subjunctivoImperfectoVos;
    }

    public void setSubjunctivoImperfectoVos(String subjunctivoImperfectoVos) {
        this.subjunctivoImperfectoVos = subjunctivoImperfectoVos;
    }

    public String getSubjunctivoImperfectoEllos() {
        return subjunctivoImperfectoEllos;
    }

    public void setSubjunctivoImperfectoEllos(String subjunctivoImperfectoEllos) {
        this.subjunctivoImperfectoEllos = subjunctivoImperfectoEllos;
    }

    public String getSubjunctivoFuturoIngles() {
        return subjunctivoFuturoIngles;
    }

    public void setSubjunctivoFuturoIngles(String subjunctivoFuturoIngles) {
        this.subjunctivoFuturoIngles = subjunctivoFuturoIngles;
    }

    public String getSubjunctivoFuturoYo() {
        return subjunctivoFuturoYo;
    }

    public void setSubjunctivoFuturoYo(String subjunctivoFuturoYo) {
        this.subjunctivoFuturoYo = subjunctivoFuturoYo;
    }

    public String getSubjunctivoFuturoTu() {
        return subjunctivoFuturoTu;
    }

    public void setSubjunctivoFuturoTu(String subjunctivoFuturoTu) {
        this.subjunctivoFuturoTu = subjunctivoFuturoTu;
    }

    public String getSubjunctivoFuturoEl() {
        return subjunctivoFuturoEl;
    }

    public void setSubjunctivoFuturoEl(String subjunctivoFuturoEl) {
        this.subjunctivoFuturoEl = subjunctivoFuturoEl;
    }

    public String getSubjunctivoFuturoNos() {
        return subjunctivoFuturoNos;
    }

    public void setSubjunctivoFuturoNos(String subjunctivoFuturoNos) {
        this.subjunctivoFuturoNos = subjunctivoFuturoNos;
    }

    public String getSubjunctivoFuturoVos() {
        return subjunctivoFuturoVos;
    }

    public void setSubjunctivoFuturoVos(String subjunctivoFuturoVos) {
        this.subjunctivoFuturoVos = subjunctivoFuturoVos;
    }

    public String getSubjunctivoFuturoEllos() {
        return subjunctivoFuturoEllos;
    }

    public void setSubjunctivoFuturoEllos(String subjunctivoFuturoEllos) {
        this.subjunctivoFuturoEllos = subjunctivoFuturoEllos;
    }

    public String getImperativeIngles() {
        return imperativeIngles;
    }

    public void setImperativeIngles(String imperativeIngles) {
        this.imperativeIngles = imperativeIngles;
    }

    public String getImperative1() {
        return imperative1;
    }

    public void setImperative1(String imperative1) {
        this.imperative1 = imperative1;
    }

    public String getImperative2() {
        return imperative2;
    }

    public void setImperative2(String imperative2) {
        this.imperative2 = imperative2;
    }

    public String getImperative3() {
        return imperative3;
    }

    public void setImperative3(String imperative3) {
        this.imperative3 = imperative3;
    }

    public String getImperative4() {
        return imperative4;
    }

    public void setImperative4(String imperative4) {
        this.imperative4 = imperative4;
    }

    public String getImperative5() {
        return imperative5;
    }

    public void setImperative5(String imperative5) {
        this.imperative5 = imperative5;
    }

    public String getImperative6() {
        return imperative6;
    }

    public void setImperative6(String imperative6) {
        this.imperative6 = imperative6;
    }

    public String getImperative7() {
        return imperative7;
    }

    public void setImperative7(String imperative7) {
        this.imperative7 = imperative7;
    }

    public String getImperative8() {
        return imperative8;
    }

    public void setImperative8(String imperative8) {
        this.imperative8 = imperative8;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(infinitivo);
        dest.writeString(infinitivoIngles);
        dest.writeString(gerund);
        dest.writeString(gerundIngles);
        dest.writeString(pastParticiple);
        dest.writeString(pastParticipleIngles);
        dest.writeString(indicativePresentIngles);
        dest.writeString(indicativePresentYo);
        dest.writeString(indicativePresentTu);
        dest.writeString(indicativePresentEl);
        dest.writeString(indicativePresentNos);
        dest.writeString(indicativePresentVos);
        dest.writeString(indicativePresentEllos);
        dest.writeString(indicativeFuturoIngles);
        dest.writeString(indicativeFuturoYo);
        dest.writeString(indicativeFuturoTu);
        dest.writeString(indicativeFuturoEl);
        dest.writeString(indicativeFuturoNos);
        dest.writeString(indicativeFuturoVos);
        dest.writeString(indicativeFuturoEllos);
        dest.writeString(indicativeImperfectoIngles);
        dest.writeString(indicativeImperfectoYo);
        dest.writeString(indicativeImperfectoTu);
        dest.writeString(indicativeImperfectoEl);
        dest.writeString(indicativeImperfectoNos);
        dest.writeString(indicativeImperfectoVos);
        dest.writeString(indicativeImperfectoEllos);
        dest.writeString(indicativePreteritoIngles);
        dest.writeString(indicativePreteritoYo);
        dest.writeString(indicativePreteritoTu);
        dest.writeString(indicativePreteritoEl);
        dest.writeString(indicativePreteritoNos);
        dest.writeString(indicativePreteritoVos);
        dest.writeString(indicativePreteritoEllos);
        dest.writeString(indicativeConditionalIngles);
        dest.writeString(indicativeConditionalYo);
        dest.writeString(indicativeConditionalTu);
        dest.writeString(indicativeConditionalEl);
        dest.writeString(indicativeConditionalNos);
        dest.writeString(indicativeConditionalVos);
        dest.writeString(indicativeConditionalEllos);
        dest.writeString(subjunctivoPresentIngles);
        dest.writeString(subjunctivoPresentYo);
        dest.writeString(subjunctivoPresentTu);
        dest.writeString(subjunctivoPresentEl);
        dest.writeString(subjunctivoPresentNos);
        dest.writeString(subjunctivoPresentVos);
        dest.writeString(subjunctivoPresentEllos);
        dest.writeString(subjunctivoImperfectoIngles);
        dest.writeString(subjunctivoImperfectoYo);
        dest.writeString(subjunctivoImperfectoTu);
        dest.writeString(subjunctivoImperfectoEl);
        dest.writeString(subjunctivoImperfectoNos);
        dest.writeString(subjunctivoImperfectoVos);
        dest.writeString(subjunctivoImperfectoEllos);
        dest.writeString(subjunctivoFuturoIngles);
        dest.writeString(subjunctivoFuturoYo);
        dest.writeString(subjunctivoFuturoTu);
        dest.writeString(subjunctivoFuturoEl);
        dest.writeString(subjunctivoFuturoNos);
        dest.writeString(subjunctivoFuturoVos);
        dest.writeString(subjunctivoFuturoEllos);
        dest.writeString(imperativeIngles);
        dest.writeString(imperative1);
        dest.writeString(imperative2);
        dest.writeString(imperative3);
        dest.writeString(imperative4);
        dest.writeString(imperative5);
        dest.writeString(imperative6);
        dest.writeString(imperative7);
        dest.writeString(imperative8);
    }
}
