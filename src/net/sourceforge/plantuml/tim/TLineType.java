/* ========================================================================
 * PlantUML : a free UML diagram generator
 * ========================================================================
 *
 * (C) Copyright 2009-2020, Arnaud Roques
 *
 * Project Info:  http://plantuml.com
 *
 * If you like this project or if you find it useful, you can support us at:
 *
 * http://plantuml.com/patreon (only 1$ per month!)
 * http://plantuml.com/paypal
 *
 * This file is part of PlantUML.
 *
 * PlantUML is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * PlantUML distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public
 * License for more details.
 *
 * You should have received a copy of the GNU General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301,
 * USA.
 *
 *
 * Original Author:  Arnaud Roques
 *
 */
package net.sourceforge.plantuml.tim;

public enum TLineType {

	PLAIN, AFFECTATION_DEFINE, AFFECTATION, ASSERT, IF, IFDEF, UNDEF, IFNDEF, ELSE, ELSEIF, ENDIF, DECLARE_FUNCTION, END_FUNCTION, RETURN, LEGACY_DEFINE, LEGACY_DEFINELONG, INCLUDE, IMPORT, STARTSUB, ENDSUB, INCLUDESUB, LOG, DUMP_MEMORY, COMMENT_SIMPLE, COMMENT_LONG_START;

	public static TLineType getFromLine(String s) {
		if (s.matches("^\\s*!define\\s+[\\p{L}_][\\p{L}_0-9]*\\(.*")) {
			return LEGACY_DEFINE;
		}
		if (s.matches("^\\s*!definelong\\s+[\\p{L}_][\\p{L}_0-9]*\\b.*")) {
			return LEGACY_DEFINELONG;
		}
		if (s.matches("^\\s*!define\\s+[\\p{L}_][\\p{L}_0-9]*\\b.*")) {
			return AFFECTATION_DEFINE;
		}
		if (s.matches("^\\s*!\\s*(local|global)?\\s*\\$?[\\p{L}_][\\p{L}_0-9]*\\s*=.*")) {
			return AFFECTATION;
		}
		if (s.matches("^\\s*'.*")) {
			return COMMENT_SIMPLE;
		}
		if (s.matches("^\\s*/'.*'/\\s*$")) {
			return COMMENT_SIMPLE;
		}
		if (s.matches("^\\s*/'.*") && s.contains("'/") == false) {
			return COMMENT_LONG_START;
		}
		if (s.matches("^\\s*!ifdef\\s+.*")) {
			return IFDEF;
		}
		if (s.matches("^\\s*!undef\\s+.*")) {
			return UNDEF;
		}
		if (s.matches("^\\s*!ifndef\\s+.*")) {
			return IFNDEF;
		}
		if (s.matches("^\\s*!assert\\s+.*")) {
			return ASSERT;
		}
		if (s.matches("^\\s*!if\\s+.*")) {
			return IF;
		}
		if (s.matches("^\\s*!(unquoted\\s|final\\s)*function\\s+\\$?[\\p{L}_][\\p{L}_0-9]*.*")) {
			return DECLARE_FUNCTION;
		}
		if (s.matches("^\\s*!else\\b.*")) {
			return ELSE;
		}
		if (s.matches("^\\s*!elseif\\b.*")) {
			return ELSEIF;
		}
		if (s.matches("^\\s*!endif\\b.*")) {
			return ENDIF;
		}
		if (s.matches("^\\s*!(endfunction|enddefinelong)\\b.*")) {
			return END_FUNCTION;
		}
		if (s.matches("^\\s*!return\\b.*")) {
			return RETURN;
		}
		if (s.matches("^\\s*!(include|includeurl|include_many|include_once)\\b.*")) {
			return INCLUDE;
		}
		if (s.matches("^\\s*!(import)\\b.*")) {
			return IMPORT;
		}
		if (s.matches("^\\s*!startsub\\s+.*")) {
			return STARTSUB;
		}
		if (s.matches("^\\s*!endsub\\b.*")) {
			return ENDSUB;
		}
		if (s.matches("^\\s*!includesub\\b.*")) {
			return INCLUDESUB;
		}
		if (s.matches("^\\s*!(log)\\b.*")) {
			return LOG;
		}
		if (s.matches("^\\s*!(dump_memory)\\b.*")) {
			return DUMP_MEMORY;
		}
		return PLAIN;
	}

	public static boolean isQuote(char ch) {
		return ch == '\"' || ch == '\'';
	}

	public static boolean isLetterOrUnderscoreOrDigit(char ch) {
		return isLetterOrUnderscore(ch) || isLatinDigit(ch);
	}

	public static boolean isLetterOrUnderscore(char ch) {
		return isLetter(ch) || ch == '_';
	}

	public static boolean isLetterOrUnderscoreOrDollar(char ch) {
		return isLetterOrUnderscore(ch) || ch == '$';
	}

	public static boolean isLetterOrDigit(char ch) {
		return isLetter(ch) || isLatinDigit(ch);
	}

	public static boolean isLetter(char ch) {
		return Character.isLetter(ch);
	}

	public static boolean isSpaceChar(char ch) {
		return Character.isSpaceChar(ch);
	}

	public static boolean isLatinDigit(char ch) {
		return ch >= '0' && ch <= '9';
	}

}
